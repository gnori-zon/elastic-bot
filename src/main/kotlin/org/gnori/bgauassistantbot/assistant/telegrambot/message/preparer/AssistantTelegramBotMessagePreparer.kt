package org.gnori.bgauassistantbot.assistant.telegrambot.message.preparer

import dev.inmo.micro_utils.common.MPPFile
import dev.inmo.tgbotapi.requests.abstracts.InputFile
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardButtons.CallbackDataInlineKeyboardButton
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardButtons.InlineKeyboardButton
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardButtons.URLInlineKeyboardButton
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup
import dev.inmo.tgbotapi.types.buttons.KeyboardMarkup
import dev.inmo.tgbotapi.utils.matrix
import org.gnori.bgauassistantbot.assistant.telegrambot.message.preparer.model.MessageRaw
import org.gnori.bgauassistantbot.assistant.telegrambot.message.sender.model.Message
import org.gnori.bgauassistantbot.assistant.telegrambot.message.sender.model.ParseMode
import org.gnori.bgauassistantbot.common.linkelement.model.LinkElement
import org.gnori.bgauassistantbot.common.linkelement.model.LinkElementType
import org.gnori.bgauassistantbot.common.phase.model.Phase
import org.gnori.bgauassistantbot.common.phase.service.PhaseService
import org.gnori.bgauassistantbot.common.telegrambot.message.preparer.TelegramBotMessagePreparer
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import java.io.FileOutputStream
import java.net.URI
import java.nio.file.Files


@Component
class AssistantTelegramBotMessagePreparer(
    private val phaseService: PhaseService
) : TelegramBotMessagePreparer<MessageRaw, Message> {

    override fun prepare(param: MessageRaw): Mono<Message> {

        return getPhase(param.phaseShortId)
            .flatMap { phase ->
                phaseService.findParentByShortId(phase.shortId)
                    .map { it.shortId }
                    .switchIfEmpty(Mono.defer { Mono.just(-1) })
                    .map { notNullShortId ->
                        val previousParentShortId = if (notNullShortId != -1) notNullShortId else null
                        // todo: split to monos -> and parallel
                        val keyboardMarkup = createKeyboardMarkup(phase, previousParentShortId)
                        val photos = createPhotos(phase.linkElements)
                        val videos = createVideos(phase.linkElements)
                        val documents = createDocuments(phase.linkElements)

                        Message(
                            param.chatId,
                            phase.description,
                            ParseMode.NULL,
                            keyboardMarkup,
                            photos,
                            videos,
                            documents
                        )
                    }
            }
    }

    // todo: move to other classes
    private fun getPhase(phaseShortId: Int?): Mono<Phase> =
        phaseShortId
            ?.let(phaseService::findByShortId)
            ?: phaseService.findFirstPhase()

    private fun createKeyboardMarkup(phase: Phase, parentShortId: Int?): KeyboardMarkup {
        val nextPhaseInlineKeyboardButtons = createCallbackDataInlineKeyboardButtons(phase.childNamesWithShortIds)
        val linkInlineKeyboardButtons = createUrlInlineKeyboardButtons(phase.linkElements)
        val backButton = createBackCallbackDataInlineKeyboardButton(parentShortId)

        return nextPhaseInlineKeyboardButtons.plus(linkInlineKeyboardButtons)
            .plusIfPresent(backButton)
            .let { replyKeyboardMarkup(it) }
    }

    private fun createBackCallbackDataInlineKeyboardButton(parentShortId: Int?): InlineKeyboardButton? {
        return parentShortId?.let {
            CallbackDataInlineKeyboardButton(text = "back", callbackData = "$it")
        }
    }

    fun <T> Collection<T>.plusIfPresent(element: T?): List<T> {
        val result = ArrayList<T>(size + 1)
        result.addAll(this)
        element?.also { result.add(it) }
        return result
    }

    private fun replyKeyboardMarkup(buttons: List<InlineKeyboardButton>): KeyboardMarkup {
        return InlineKeyboardMarkup(keyboard = matrix { buttons.forEach { add(listOf(it)) } })
    }

    private fun createCallbackDataInlineKeyboardButtons(
        phaseNamesWithShortIds: List<Pair<String, Int>>
    ): List<InlineKeyboardButton> {
        return phaseNamesWithShortIds.map { (name, shortId) ->
            CallbackDataInlineKeyboardButton(text = name, callbackData = shortId.toString())
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
            .mapNotNull { linkElement -> loadBy(linkElement.name, linkElement.link) }
            .map { InputFile(it) }
    }

    private fun createVideos(linkElements: List<LinkElement>): List<InputFile> {
        return linkElements.filterType(LinkElementType.VIDEO)
            .mapNotNull { linkElement -> loadBy(linkElement.name, linkElement.link) }
            .map { InputFile(it) }
    }

    private fun createDocuments(linkElements: List<LinkElement>): List<InputFile> {
        return linkElements.filterType(LinkElementType.DOCUMENT)
            .mapNotNull { linkElement -> loadBy(linkElement.name, linkElement.link) }
            .map { InputFile(it) }
    }

    private fun loadBy(filename: String, fileLink: String): MPPFile? {
        try {
            val remoteFileBytes = URI(fileLink).toURL().readBytes()
            val file = Files.createTempFile("", filename).toFile()
            FileOutputStream(file).use { outputStream ->
                outputStream.write(remoteFileBytes)
            }
            return file
        } catch (ex: Exception) {
            return null;
        }
    }
}

private fun List<LinkElement>.filterType(type: LinkElementType): List<LinkElement> =
    this.filter { it.type == type }