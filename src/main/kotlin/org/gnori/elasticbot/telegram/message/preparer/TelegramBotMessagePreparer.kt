package org.gnori.elasticbot.telegram.message.preparer

import dev.inmo.tgbotapi.requests.abstracts.InputFile
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardButtons.CallbackDataInlineKeyboardButton
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardButtons.InlineKeyboardButton
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardButtons.URLInlineKeyboardButton
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup
import dev.inmo.tgbotapi.types.buttons.KeyboardMarkup
import dev.inmo.tgbotapi.utils.matrix
import io.ktor.utils.io.streams.*
import org.gnori.elasticbot.common.ext.addIfPresent
import org.gnori.elasticbot.common.linkelement.model.LinkElement
import org.gnori.elasticbot.common.linkelement.model.LinkElementType
import org.gnori.elasticbot.common.phase.model.FlowNode
import org.gnori.elasticbot.common.phase.service.FlowNodeService
import org.gnori.elasticbot.common.telegrambot.message.preparer.TelegramBotMessagePreparer
import org.gnori.elasticbot.telegram.file.loader.FileLoader
import org.gnori.elasticbot.telegram.message.preparer.model.MessageRaw
import org.gnori.elasticbot.telegram.message.sender.model.*
import org.gnori.elasticbot.telegram.message.sender.model.sending.Message
import org.springframework.stereotype.Component
import java.io.ByteArrayInputStream


@Component
class TelegramBotMessagePreparer(
    private val flowNodeService: FlowNodeService,
    private val fileLoader: FileLoader
) : TelegramBotMessagePreparer<MessageRaw, Message> {

    override fun prepare(param: MessageRaw): Message {
        val flowNode = findFlowNode(param.id)
            ?: throw RuntimeException("bad try find flow node by relation id: ${param.id}")

        val keyboardMarkup = createKeyboardMarkup(flowNode, param.withBack)
        val headerMedia = createHeaderMedia(flowNode.headerLinkElement)
        val photos = createPhotos(flowNode.linkElements)
        val videos = createVideos(flowNode.linkElements)
        val documents = createDocuments(flowNode.linkElements)

        return Message(
            param.chatId,
            flowNode.description,
            headerMedia,
            ParseMode.of(flowNode.descriptionFormatType),
            keyboardMarkup,
            photos,
            videos,
            documents
        )
    }

    private fun findFlowNode(id: String?): FlowNode? {
        if (id == null) {
            return flowNodeService.findStart()
        }
        return flowNodeService.findById(id)
    }

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
        flowNode: FlowNode,
        withBack: Boolean,
    ): KeyboardMarkup {
        val parentId = flowNode.parentId
        val childFlowNodeButtons = createCallbackDataInlineKeyboardButtons(flowNode.childFlowNodes)
        val linkInlineKeyboardButtons = createUrlInlineKeyboardButtons(flowNode.linkElements)
        val backButton = if (withBack && parentId != null) {
                createBackCallbackDataInlineKeyboardButton(parentId)
            } else {
                null
            }

        val buttons = childFlowNodeButtons.plus(linkInlineKeyboardButtons)
            .addIfPresent(backButton)
        return keyboardMarkup(buttons)
    }

    private fun createBackCallbackDataInlineKeyboardButton(id: String): InlineKeyboardButton {
        return CallbackDataInlineKeyboardButton(text = "back", callbackData = id)
    }

    private fun keyboardMarkup(buttons: List<InlineKeyboardButton>): KeyboardMarkup {
        return InlineKeyboardMarkup(keyboard = matrix { buttons.forEach { add(listOf(it)) } })
    }

    private fun createCallbackDataInlineKeyboardButtons(flowNodes: List<Pair<String, String>>): List<InlineKeyboardButton> {
        return flowNodes.map { (name, relationId) ->
            CallbackDataInlineKeyboardButton(text = name, callbackData = relationId)
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