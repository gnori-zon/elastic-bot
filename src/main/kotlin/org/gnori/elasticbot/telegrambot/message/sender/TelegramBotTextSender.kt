package org.gnori.elasticbot.telegrambot.message.sender

import dev.inmo.tgbotapi.extensions.api.send.send
import kotlinx.coroutines.runBlocking
import org.gnori.elasticbot.telegrambot.TelegramBotKeeper
import org.gnori.elasticbot.telegrambot.message.sender.model.sending.TextWithChatId
import org.gnori.elasticbot.common.ext.toChatId
import org.gnori.elasticbot.common.ext.toInmoParseMode
import org.gnori.elasticbot.common.telegrambot.message.sender.TelegramBotMessageSender
import org.springframework.stereotype.Component

@Component
class TelegramBotTextSender(
    botKeeper: TelegramBotKeeper
) : TelegramBotMessageSender<TextWithChatId> {

    private val bot = botKeeper.bot

    override fun send(params: TextWithChatId): Boolean {

        return runBlocking {
            bot.send(
                chatId = params.chatId.toChatId(),
                parseMode = params.text.parseMode.toInmoParseMode(),
                text = params.text.text,
                replyMarkup = params.text.keyboardMarkup
            ).let { true }
        }
    }
}