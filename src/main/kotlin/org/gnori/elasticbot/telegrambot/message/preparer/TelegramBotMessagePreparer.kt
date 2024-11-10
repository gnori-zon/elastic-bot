package org.gnori.elasticbot.telegrambot.message.preparer

import dev.inmo.tgbotapi.requests.abstracts.InputFile
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardButtons.CallbackDataInlineKeyboardButton
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardButtons.InlineKeyboardButton
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardButtons.URLInlineKeyboardButton
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup
import dev.inmo.tgbotapi.types.buttons.KeyboardMarkup
import dev.inmo.tgbotapi.utils.matrix
import io.ktor.utils.io.streams.*
import org.gnori.elasticbot.telegrambot.file.loader.FileLoader
import org.gnori.elasticbot.telegrambot.message.callback.CallbackDataType
import org.gnori.elasticbot.telegrambot.message.preparer.model.MessageRaw
import org.gnori.elasticbot.telegrambot.message.sender.model.*
import org.gnori.elasticbot.telegrambot.message.sender.model.sending.Message
import org.gnori.elasticbot.common.ext.addIfPresent
import org.gnori.elasticbot.common.linkelement.model.LinkElement
import org.gnori.elasticbot.common.linkelement.model.LinkElementType
import org.gnori.elasticbot.common.named.query.service.NamedQueryService
import org.gnori.elasticbot.common.phase.action.model.PhaseAction
import org.gnori.elasticbot.common.phase.action.service.PhaseActionService
import org.gnori.elasticbot.common.phase.description.model.PhaseDescriptionType
import org.gnori.elasticbot.common.phase.model.Phase
import org.gnori.elasticbot.common.phase.service.PhaseService
import org.gnori.elasticbot.common.telegrambot.message.preparer.TelegramBotMessagePreparer
import org.springframework.stereotype.Component
import java.io.ByteArrayInputStream


@Component
class TelegramBotMessagePreparer(
    private val phaseService: PhaseService,
    private val actionService: PhaseActionService,
    private val namedQueryService: NamedQueryService,
    private val fileLoader: FileLoader
) : TelegramBotMessagePreparer<MessageRaw, Message> {

    override fun prepare(param: MessageRaw): Message {

        val phase = doActionIfNeedAndGetPhaseId(param)
            .let { redirectPhaseId -> getPhase(redirectPhaseId, param.descriptionType) }

        return phaseService.findParentByShortId(phase.shortId)
            .let { parent ->
                val fieldContext = mapOf("user_id" to param.user.id, "phase_id" to phase.id)
                val keyboardMarkup = createKeyboardMarkup(phase, parent?.shortId, param.withBack, fieldContext)
                val headerMedia = createHeaderMedia(phase.headerLinkElement)
                val photos = createPhotos(phase.linkElements)
                val videos = createVideos(phase.linkElements)
                val documents = createDocuments(phase.linkElements)

                Message(
                    param.chatId,
                    phase.description,
                    headerMedia,
                    ParseMode.of(phase.descriptionFormatType),
                    keyboardMarkup,
                    photos,
                    videos,
                    documents
                )
            }
    }

    private fun doActionIfNeedAndGetPhaseId(param: MessageRaw): Int? {

        return if (CallbackDataType.ACTION == param.callbackDataType && param.callBackDataShortId != null) {
            actionService.findByShortId(param.callBackDataShortId)
                ?.let { action ->
                    val fieldContext = mapOf(
                        "user_id" to param.user.id,
                        "phase_id" to action.fromPhaseId
                    )
                    namedQueryService.execute(action.actionNamedQuery, fieldContext)
                    action.redirectPhaseShortId
                }
        } else {
            param.callBackDataShortId
        }
    }

    // todo: move to other classes
    private fun getPhase(phaseShortId: Int?, descriptionType: PhaseDescriptionType): Phase =
        phaseShortId
            ?.let { shortId -> phaseService.findByShortId(shortId, descriptionType) }
            ?: phaseService.findFirstPhase(descriptionType)!! // todo: replace on warning

    private fun createHeaderMedia(headerLinkElement: LinkElement?): Media? {

        return headerLinkElement?.let {
            fileLoader.loadBy(it.link)?.let { bytes ->
                InputFile.fromInput(it.name, ByteArrayInputStream(bytes)::asInput)
            }
        }?.let {
            when (headerLinkElement.type) {
                LinkElementType.PHOTO -> Photo(it)
                LinkElementType.VIDEO -> Video(it)
                LinkElementType.DOCUMENT -> Document(it)
                else -> throw IllegalStateException("not supported type" + headerLinkElement.type)
            }
        }
    }

    private fun createKeyboardMarkup(
        phase: Phase,
        parentShortId: Int?,
        withBack: Boolean,
        fieldContext: Map<String, Any>,
    ): KeyboardMarkup =
        createCallbackDataInlineKeyboardButtons(phase.actions, fieldContext)
            .let { actionButtons ->
                val nextPhaseInlineKeyboardButtons = createCallbackDataInlineKeyboardButtons(phase.childNamesWithShortIds)
                val linkInlineKeyboardButtons = createUrlInlineKeyboardButtons(phase.linkElements)
                val backButton =
                    if (withBack) {
                        createBackCallbackDataInlineKeyboardButton(parentShortId)
                    } else {
                        null
                    }

                nextPhaseInlineKeyboardButtons.plus(linkInlineKeyboardButtons).plus(actionButtons)
                    .addIfPresent(backButton)
                    .let { replyKeyboardMarkup(it) }
            }

    private fun createBackCallbackDataInlineKeyboardButton(parentShortId: Int?): InlineKeyboardButton? {
        return parentShortId?.let {
            CallbackDataInlineKeyboardButton(text = "Назад", callbackData = "$it;${CallbackDataType.§PHASE}")
        }
    }

    private fun replyKeyboardMarkup(buttons: List<InlineKeyboardButton>): KeyboardMarkup {
        return InlineKeyboardMarkup(keyboard = matrix { buttons.forEach { add(listOf(it)) } })
    }

    private fun createCallbackDataInlineKeyboardButtons(
        phaseNamesWithShortIds: List<Pair<String, Int>>
    ): List<InlineKeyboardButton> {
        return phaseNamesWithShortIds.map { (name, shortId) ->
            CallbackDataInlineKeyboardButton(text = name, callbackData = "$shortId;${CallbackDataType.PHASE}")
        }
    }

    private fun createCallbackDataInlineKeyboardButtons(
        actions: List<PhaseAction>,
        fieldContext: Map<String, Any>
    ): List<InlineKeyboardButton> {
        return actions.mapNotNull { action ->
                if (action.displayConditionNamedQuery == null) {
                    action
                } else {
                    namedQueryService.execute(action.displayConditionNamedQuery!!, fieldContext)
                        .let {
                            val firstKey = it[0].keys.firstOrNull()
                            firstKey?.let { key -> it[0][key] as? Boolean } ?: false
                        }.let { isDisplay ->
                            if (isDisplay) {
                                action
                            } else {
                               null
                            }
                        }
                }
            }
            .map { action ->
                CallbackDataInlineKeyboardButton(
                    text = action.name,
                    callbackData = "${action.shortId};${CallbackDataType.ACTION}"
                )
            }
    }

    private fun createUrlInlineKeyboardButtons(linkElements: List<LinkElement>): List<InlineKeyboardButton> {
        return linkElements.filterType(LinkElementType.URL)
            .map { linkElement ->
                URLInlineKeyboardButton(text = linkElement.name, url = linkElement.link)
            }
    }

    private fun createPhotos(linkElements: List<LinkElement>): List<InputFile> {
        return linkElements.filterType(LinkElementType.PHOTO)
            .mapNotNull { linkElement ->
                fileLoader.loadBy(linkElement.link)?.let {
                    InputFile.fromInput(linkElement.name, ByteArrayInputStream(it)::asInput)
                }
            }
    }

    private fun createVideos(linkElements: List<LinkElement>): List<InputFile> {
        return linkElements.filterType(LinkElementType.VIDEO)
            .mapNotNull { linkElement ->
                fileLoader.loadBy(linkElement.link)?.let {
                    InputFile.fromInput(linkElement.name, ByteArrayInputStream(it)::asInput)
                }
            }
    }

    private fun createDocuments(linkElements: List<LinkElement>): List<InputFile> {
        return linkElements.filterType(LinkElementType.DOCUMENT)
            .mapNotNull { linkElement ->
                fileLoader.loadBy(linkElement.link)?.let {
                    InputFile.fromInput(linkElement.name, ByteArrayInputStream(it)::asInput)
                }
            }
    }
}

private fun List<LinkElement>.filterType(type: LinkElementType): List<LinkElement> =
    this.filter { it.type == type }