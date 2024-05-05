package org.gnori.bgauassistantbot.assistant.telegrambot.message.sender

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.extensions.api.deleteMessage
import dev.inmo.tgbotapi.extensions.api.send.sendMessage
import dev.inmo.tgbotapi.types.ChatId
import dev.inmo.tgbotapi.types.RawChatId
import dev.inmo.tgbotapi.types.message.HTMLParseMode
import dev.inmo.tgbotapi.types.message.MarkdownParseMode
import kotlinx.coroutines.runBlocking
import org.gnori.bgauassistantbot.assistant.telegrambot.AssistantTelegramBotData
import org.gnori.bgauassistantbot.assistant.telegrambot.message.sender.model.ParseMode
import org.gnori.bgauassistantbot.assistant.telegrambot.message.sender.model.SendText
import org.gnori.bgauassistantbot.common.telegrambot.message.sender.TelegramBotMessageSender
import org.springframework.stereotype.Component

@Component
class AssistantTelegramBotMessageSender(
    botData: AssistantTelegramBotData
): TelegramBotMessageSender<SendText> {

    private val telegramBot: TelegramBot = botData.telegramBot

    override fun send(params: SendText): Long = runBlocking {

        val chatId = ChatId(RawChatId(params.chatId))
        val parseMode = when (params.parseMode) {
            ParseMode.HTML -> HTMLParseMode
            ParseMode.MARKDOWN -> MarkdownParseMode
            ParseMode.NULL -> null
        }

        telegramBot.sendMessage(
            chatId = chatId,
            text = params.text,
            parseMode = parseMode
        ).messageId.long
    }
}