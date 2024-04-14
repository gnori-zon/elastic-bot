package org.gnori.bgauassistantbot.assistant.telegrambot.sender

import dev.inmo.tgbotapi.extensions.api.send.sendMessage
import dev.inmo.tgbotapi.types.ChatId
import dev.inmo.tgbotapi.types.RawChatId
import dev.inmo.tgbotapi.types.message.HTMLParseMode
import dev.inmo.tgbotapi.types.message.MarkdownParseMode
import kotlinx.coroutines.runBlocking
import org.gnori.bgauassistantbot.assistant.telegrambot.AssistantTelegramBotData
import org.gnori.bgauassistantbot.assistant.telegrambot.sender.model.ParseMode
import org.gnori.bgauassistantbot.assistant.telegrambot.sender.model.SendText
import org.gnori.bgauassistantbot.common.telegrambot.sender.TelegramBotSender
import org.springframework.stereotype.Component

@Component
class AssistantTelegramBotSender(
    private val botData: AssistantTelegramBotData
): TelegramBotSender<SendText> {


    override fun send(params: SendText): Long = runBlocking {

        val chatId = ChatId(RawChatId(params.chatId))
        val parseMode = when (params.parseMode) {
            ParseMode.HTML -> HTMLParseMode
            ParseMode.MARKDOWN -> MarkdownParseMode
            ParseMode.NULL -> null
        }

        botData.telegramBot.sendMessage(
            chatId = chatId,
            text = params.text,
            parseMode = parseMode
        ).messageId.long
    }
}