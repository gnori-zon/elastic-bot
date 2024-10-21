package org.gnori.bgauassistantbot.assistant.telegrambot.update.processor

import dev.inmo.tgbotapi.extensions.utils.asCallbackQueryUpdate
import dev.inmo.tgbotapi.extensions.utils.extensions.raw.data
import dev.inmo.tgbotapi.extensions.utils.extensions.raw.message
import dev.inmo.tgbotapi.extensions.utils.extensions.sourceChat
import dev.inmo.tgbotapi.extensions.utils.extensions.sourceUser
import dev.inmo.tgbotapi.types.update.abstracts.Update
import org.gnori.bgauassistantbot.assistant.telegrambot.authorizer.user.model.UserAuthorizeParam
import org.gnori.bgauassistantbot.assistant.telegrambot.message.callback.CallbackDataType
import org.gnori.bgauassistantbot.assistant.telegrambot.message.deleter.model.DeleteMessageParam
import org.gnori.bgauassistantbot.assistant.telegrambot.message.preparer.model.MessageRaw
import org.gnori.bgauassistantbot.assistant.telegrambot.message.sender.model.sending.Message
import org.gnori.bgauassistantbot.common.telegrambot.authorizer.user.UserAuthorizer
import org.gnori.bgauassistantbot.common.telegrambot.message.deleter.TelegramBotMessageDeleter
import org.gnori.bgauassistantbot.common.telegrambot.message.preparer.TelegramBotMessagePreparer
import org.gnori.bgauassistantbot.common.telegrambot.message.sender.TelegramBotMessageSender
import org.gnori.bgauassistantbot.common.telegrambot.update.processor.TelegramBotUpdateProcessor
import org.gnori.bgauassistantbot.common.user.model.User
import org.springframework.stereotype.Component

@Component
final class AssistantTelegramBotUpdateProcessor(
    private val userAuthorizer: UserAuthorizer<UserAuthorizeParam>,
    private val messageDeleter: TelegramBotMessageDeleter<DeleteMessageParam>,
    private val messagePreparer: TelegramBotMessagePreparer<MessageRaw, Message>,
    private val messageSender: TelegramBotMessageSender<Message>
) : TelegramBotUpdateProcessor {

    override fun process(update: Update) {
        authorizeUser(update)
            ?.let { user ->
                clearLastMessage(update)
                sendNewMessage(update, user)
            }
    }

    private fun authorizeUser(update: Update): User? =
        update.sourceUser()
            ?.let {
                UserAuthorizeParam(
                    username = it.username?.username ?: "",
                    chatId = it.id.chatId.long
                )
            }
            ?.let(userAuthorizer::authorize)

    private fun clearLastMessage(update: Update): Boolean =
        update.asCallbackQueryUpdate()
            ?.let { it.data.message }
            ?.let {
                DeleteMessageParam(
                    chatId = it.chat.id.chatId.long,
                    messageId = it.messageId.long
                )
            }
            ?.let(messageDeleter::delete) ?: false

    private fun sendNewMessage(update: Update, user: User): Boolean =
        update.sourceChat()
            ?.let { chat ->
                val chatId = chat.id.chatId.long
                // todo: check is phase or is action
                val (shortId, type) = update.asCallbackQueryUpdate()
                    ?.let {
                        val callbackDataParams = it.data.data?.split(";") ?: emptyList()
                        Pair(
                            callbackDataParams[0].toIntOrNull(),
                            callbackDataParams[1].toIntOrNull()?.let(CallbackDataType::of)
                        )
                    } ?: Pair(null, null)


                messagePreparer.prepare(MessageRaw(chatId, user, shortId, type))
                    .let { message -> messageSender.send(message) }
            } ?: false
}