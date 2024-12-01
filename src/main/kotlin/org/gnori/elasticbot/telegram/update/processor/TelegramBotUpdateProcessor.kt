package org.gnori.elasticbot.telegram.update.processor

import org.gnori.elasticbot.common.ext.logger
import org.gnori.elasticbot.common.telegrambot.authorizer.user.UserAuthorizer
import org.gnori.elasticbot.common.telegrambot.message.deleter.TelegramBotMessageDeleter
import org.gnori.elasticbot.common.telegrambot.message.preparer.TelegramBotMessagePreparer
import org.gnori.elasticbot.common.telegrambot.message.sender.TelegramBotMessageSender
import org.gnori.elasticbot.common.telegrambot.update.processor.TelegramBotUpdateProcessor
import org.gnori.elasticbot.common.user.model.User
import org.gnori.elasticbot.telegram.authorizer.user.model.UserAuthorizationParams
import org.gnori.elasticbot.telegram.ext.callbackData
import org.gnori.elasticbot.telegram.mapper.toDeleteMessageParams
import org.gnori.elasticbot.telegram.mapper.toMessageRaw
import org.gnori.elasticbot.telegram.mapper.toUserAuthorizationParams
import org.gnori.elasticbot.telegram.message.deleter.model.DeleteMessageParams
import org.gnori.elasticbot.telegram.message.preparer.model.MessageRaw
import org.gnori.elasticbot.telegram.message.sender.model.sending.Message
import org.gnori.elasticbot.telegram.update.processor.model.Event
import org.springframework.stereotype.Component

@Component
final class TelegramBotUpdateProcessor(
    private val userAuthorizer: UserAuthorizer<UserAuthorizationParams>,
    private val messageDeleter: TelegramBotMessageDeleter<DeleteMessageParams>,
    private val messagePreparer: TelegramBotMessagePreparer<MessageRaw, Message>,
    private val messageSender: TelegramBotMessageSender<Message>
) : TelegramBotUpdateProcessor {

    private val logger by logger()

    override fun process(event: Event) {
        logger.info("start process event: ${event.updateId}")
        try {
            innerProcess(event)
        } catch (exception: Exception) {
            logger.info("bad process update: ${event.updateId}; ${exception.message} \n ${exception.stackTrace}")
        } finally {
            logger.info("finish process update: ${event.updateId}")
        }
    }

    private fun innerProcess(event: Event) {
        val authorizedUser = userAuthorizer.authorize(event.toUserAuthorizationParams())
        messageDeleter.delete(event.toDeleteMessageParams())
        val message = messagePreparer.prepare(event.toMessageRaw(authorizedUser))
        messageSender.send(message)
    }
}