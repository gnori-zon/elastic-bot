package org.gnori.bgauassistantbot.assistant.telegrambot.message.deleter

import dev.inmo.tgbotapi.extensions.api.deleteMessage
import dev.inmo.tgbotapi.types.ChatId
import dev.inmo.tgbotapi.types.MessageId
import dev.inmo.tgbotapi.types.RawChatId
import kotlinx.coroutines.reactor.mono
import kotlinx.coroutines.runBlocking
import org.gnori.bgauassistantbot.assistant.telegrambot.AssistantTelegramBotData
import org.gnori.bgauassistantbot.assistant.telegrambot.message.deleter.model.DeleteMessageParam
import org.gnori.bgauassistantbot.common.telegrambot.message.deleter.TelegramBotMessageDeleter
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class AssistantTelegramBotMessageDeleter(
    botData: AssistantTelegramBotData
) : TelegramBotMessageDeleter<DeleteMessageParam> {

    private val telegramBot = botData.telegramBot

    override fun delete(param: DeleteMessageParam): Mono<Boolean> =
        Mono.defer {
            val chatId = ChatId(RawChatId(param.chatId))
            val messageId = MessageId(param.messageId)

            mono { telegramBot.deleteMessage(chatId, messageId) }
        }
}