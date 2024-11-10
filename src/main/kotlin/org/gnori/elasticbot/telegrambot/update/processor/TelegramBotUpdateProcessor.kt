package org.gnori.elasticbot.telegrambot.update.processor

import dev.inmo.tgbotapi.extensions.utils.asCallbackQueryUpdate
import dev.inmo.tgbotapi.extensions.utils.extensions.raw.data
import dev.inmo.tgbotapi.extensions.utils.extensions.raw.message
import dev.inmo.tgbotapi.extensions.utils.extensions.sourceChat
import dev.inmo.tgbotapi.extensions.utils.extensions.sourceUser
import dev.inmo.tgbotapi.types.update.abstracts.Update
import org.gnori.elasticbot.common.ext.logger
import org.gnori.elasticbot.telegrambot.authorizer.user.model.UserAuthorizationParams
import org.gnori.elasticbot.telegrambot.message.callback.CallbackDataType
import org.gnori.elasticbot.telegrambot.message.deleter.model.DeleteMessageParam
import org.gnori.elasticbot.telegrambot.message.preparer.model.MessageRaw
import org.gnori.elasticbot.telegrambot.message.sender.model.sending.Message
import org.gnori.elasticbot.common.telegrambot.authorizer.user.UserAuthorizer
import org.gnori.elasticbot.common.telegrambot.message.deleter.TelegramBotMessageDeleter
import org.gnori.elasticbot.common.telegrambot.message.preparer.TelegramBotMessagePreparer
import org.gnori.elasticbot.common.telegrambot.message.sender.TelegramBotMessageSender
import org.gnori.elasticbot.common.telegrambot.update.processor.TelegramBotUpdateProcessor
import org.gnori.elasticbot.common.user.model.User
import org.gnori.elasticbot.telegrambot.update.processor.model.Event
import org.springframework.stereotype.Component
import java.util.*

@Component
final class TelegramBotUpdateProcessor(
    private val userAuthorizer: UserAuthorizer<UserAuthorizationParams>,
    private val messageDeleter: TelegramBotMessageDeleter<DeleteMessageParam>,
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
        val authorizedUser = authorizeUser(event)
        clearLastMessage(event)
        sendNewMessage(event, authorizedUser)
    }

    // move to extension
    private fun authorizeUser(event: Event): User {
        val params = UserAuthorizationParams(
            username = it.username?.username ?: "",
            chatId = it.id.chatId.long
        )
        return userAuthorizer.authorize(params)
    }

    // move to service
    private fun clearLastMessage(update: Update): Boolean {
        val message = update.asCallbackQueryUpdate()?.let { it.data.message }
        if (message == null) {
            return false
        }
        val deleteMessage = DeleteMessageParam(chatId = it.chat.id.chatId.long, messageId = it.messageId.long)
        return messageDeleter.delete(deleteMessage)
    }

    // may be create extractor
    private fun sendNewMessage(update: Update, user: User): Boolean {
        val chat = update.sourceChat() ?: return false
        val chatId = chat.id.chatId.long
        val flowNodeId = update.asCallbackQueryUpdate()?.let { it.data.data }
            ?.let(UUID::fromString)


        val message = messagePreparer.prepare(MessageRaw(chatId, user, flowNodeId))
        return messageSender.send(message)
    }
}