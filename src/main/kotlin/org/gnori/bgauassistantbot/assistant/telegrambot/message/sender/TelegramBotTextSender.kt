package org.gnori.bgauassistantbot.assistant.telegrambot.message.sender

import dev.inmo.tgbotapi.extensions.api.send.send
import kotlinx.coroutines.runBlocking
import org.gnori.bgauassistantbot.assistant.telegrambot.AssistantTelegramBotData
import org.gnori.bgauassistantbot.assistant.telegrambot.message.sender.model.sending.TextWithChatId
import org.gnori.bgauassistantbot.common.ext.toChatId
import org.gnori.bgauassistantbot.common.ext.toInmoParseMode
import org.gnori.bgauassistantbot.common.telegrambot.message.sender.TelegramBotMessageSender
import org.springframework.stereotype.Component

@Component
class TelegramBotTextSender(
    botData: AssistantTelegramBotData
) : TelegramBotMessageSender<TextWithChatId> {

    private val telegramBot = botData.telegramBot

    override fun send(params: TextWithChatId): Boolean {

        return runBlocking {
            telegramBot.send(
                chatId = params.chatId.toChatId(),
                parseMode = params.text.parseMode.toInmoParseMode(),
                text = params.text.text,
                replyMarkup = params.text.keyboardMarkup
            ).let { true }
        }
    }
}