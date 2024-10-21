package org.gnori.bgauassistantbot.assistant.telegrambot.message.sender

import kotlinx.coroutines.runBlocking
import org.gnori.bgauassistantbot.assistant.telegrambot.message.sender.model.sending.*
import org.gnori.bgauassistantbot.common.telegrambot.message.sender.TelegramBotMessageSender
import org.springframework.stereotype.Component

@Component
class AssistantTelegramBotMessageSender(
    private val visualMediaGroupSender: TelegramBotMessageSender<VisualMediaGroup>,
    private val textSender: TelegramBotMessageSender<TextWithChatId>,
    private val textWithMediaSender: TelegramBotMessageSender<TextWithMedia>,
    private val documentMediaGroupSender: TelegramBotMessageSender<DocumentMediaGroup>
) : TelegramBotMessageSender<Message> {

    override fun send(params: Message): Boolean = runBlocking {

        when (params.textWithMedia) {
            null -> textSender.send(params.textWithChatId)
            else -> textWithMediaSender.send(params.textWithMedia)
        }.let {
            visualMediaGroupSender.send(params.visualMedia)
            documentMediaGroupSender.send(params.documentMedia)
        }
    }
}
