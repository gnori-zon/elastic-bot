package org.gnori.bgauassistantbot.assistant.telegrambot.update.processor

import dev.inmo.tgbotapi.extensions.utils.asCallbackQueryUpdate
import dev.inmo.tgbotapi.extensions.utils.extensions.raw.data
import dev.inmo.tgbotapi.extensions.utils.extensions.raw.message
import dev.inmo.tgbotapi.extensions.utils.extensions.sourceChat
import dev.inmo.tgbotapi.extensions.utils.extensions.sourceUser
import dev.inmo.tgbotapi.types.update.abstracts.Update
import org.gnori.bgauassistantbot.common.telegrambot.authorizer.user.UserAuthorizer
import org.gnori.bgauassistantbot.assistant.telegrambot.authorizer.user.model.UserAuthorizeParam
import org.gnori.bgauassistantbot.assistant.telegrambot.message.deleter.AssistantTelegramBotMessageDeleter
import org.gnori.bgauassistantbot.assistant.telegrambot.message.deleter.model.DeleteMessageParam
import org.gnori.bgauassistantbot.assistant.telegrambot.message.preparer.model.MessageRaw
import org.gnori.bgauassistantbot.assistant.telegrambot.message.sender.model.Message
import org.gnori.bgauassistantbot.common.telegrambot.message.deleter.TelegramBotMessageDeleter
import org.gnori.bgauassistantbot.common.telegrambot.message.preparer.TelegramBotMessagePreparer
import org.gnori.bgauassistantbot.common.telegrambot.message.sender.TelegramBotMessageSender
import org.gnori.bgauassistantbot.common.telegrambot.update.consumer.TelegramBotConsumer
import org.gnori.bgauassistantbot.common.telegrambot.update.processor.TelegramBotUpdateProcessor
import org.gnori.bgauassistantbot.common.user.model.User
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers

@Component
final class AssistantTelegramBotUpdateProcessor(
    telegramBotConsumer: TelegramBotConsumer,
    private val userAuthorizer: UserAuthorizer<UserAuthorizeParam>,
    private val messageDeleter: TelegramBotMessageDeleter<DeleteMessageParam>,
    private val messagePreparer: TelegramBotMessagePreparer<MessageRaw, Message>,
    private val messageSender: TelegramBotMessageSender<Message>
) : TelegramBotUpdateProcessor {

    init {
        telegramBotConsumer.updates
            .doOnNext { update ->
                Mono.just(update)
                    .doOnNext(this::process)
                    .subscribeOn(Schedulers.boundedElastic())
                    .subscribe()
            }
            .subscribeOn(Schedulers.single())
            .subscribe()
    }

    override fun process(update: Update) {
        authorizeUser(update)
            .flatMap { user ->
                clearLastMessage(update).flatMap { isClear ->
                    sendNewMessage(update)
                }
            }
            .subscribe()
    }

    private fun authorizeUser(update: Update): Mono<User> =
        update.sourceUser()
            ?.let {
                UserAuthorizeParam(
                    username = it.username?.username ?: "",
                    chatId = it.id.chatId.long
                )
            }
            ?.let(userAuthorizer::authorize) ?: Mono.empty()

    private fun clearLastMessage(update: Update): Mono<Boolean> =
        update.asCallbackQueryUpdate()
            ?.let { it.data.message }
            ?.let {
                DeleteMessageParam(
                    chatId = it.chat.id.chatId.long,
                    messageId = it.messageId.long
                )
            }
            ?.let(messageDeleter::delete) ?: Mono.just(false)

    private fun sendNewMessage(update: Update): Mono<Boolean> =
        update.sourceChat()
            ?.let { chat ->
                val chatId = chat.id.chatId.long
                val phaseShortId = update.asCallbackQueryUpdate()
                    ?.let { it.data.data?.toIntOrNull() }


                messagePreparer.prepare(MessageRaw(chatId, phaseShortId))
                    .flatMap(messageSender::send)
            } ?: Mono.defer { Mono.just(false) }
}