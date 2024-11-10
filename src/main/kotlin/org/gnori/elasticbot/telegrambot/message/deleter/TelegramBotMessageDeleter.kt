package org.gnori.elasticbot.telegrambot.message.deleter

import dev.inmo.tgbotapi.extensions.api.deleteMessage
import dev.inmo.tgbotapi.types.ChatId
import dev.inmo.tgbotapi.types.MessageId
import dev.inmo.tgbotapi.types.RawChatId
import kotlinx.coroutines.runBlocking
import mu.KLogging
import org.gnori.elasticbot.common.telegrambot.message.deleter.TelegramBotMessageDeleter
import org.gnori.elasticbot.telegrambot.TelegramBotKeeper
import org.gnori.elasticbot.telegrambot.message.deleter.model.DeleteMessageParam
import org.springframework.stereotype.Component

@Component
class TelegramBotMessageDeleter(
    botKeeper: TelegramBotKeeper
) : TelegramBotMessageDeleter<DeleteMessageParam> {

    private val logger = KLogging().logger
    private val bot = botKeeper.bot

    override fun delete(param: DeleteMessageParam): Boolean {
        try {
            val chatId = ChatId(RawChatId(param.chatId))
            val messageId = MessageId(param.messageId)
            return runBlocking { bot.deleteMessage(chatId, messageId) }
        } catch (exception: Exception) {
            logger.info("Bad deleting message by chatId: ${param.chatId}, messageId: ${param.messageId}")
            return false
        }
    }
}