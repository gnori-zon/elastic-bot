package org.gnori.elasticbot.telegram.message.deleter

import dev.inmo.tgbotapi.extensions.api.deleteMessage
import dev.inmo.tgbotapi.types.ChatId
import dev.inmo.tgbotapi.types.MessageId
import kotlinx.coroutines.runBlocking
import org.gnori.elasticbot.common.ext.logger
import org.gnori.elasticbot.common.telegrambot.message.deleter.TelegramBotMessageDeleter
import org.gnori.elasticbot.telegram.TelegramBotKeeper
import org.gnori.elasticbot.telegram.ext.from
import org.gnori.elasticbot.telegram.message.deleter.model.DeleteMessageParams
import org.springframework.stereotype.Component

@Component
class TelegramBotMessageDeleter(
    botKeeper: TelegramBotKeeper
) : TelegramBotMessageDeleter<DeleteMessageParams> {

    private val logger by logger()
    private val bot = botKeeper.bot

    override fun delete(param: DeleteMessageParams): Boolean {
        try {
            val chatId = ChatId.from(param.chatId)
            val messageId = MessageId.from(param.messageId)
            return runBlocking {
                bot.deleteMessage(chatId, messageId)
            }
        } catch (exception: Exception) {
            logger.info("Bad deleting message by chatId: ${param.chatId}, messageId: ${param.messageId}")
            return false
        }
    }
}