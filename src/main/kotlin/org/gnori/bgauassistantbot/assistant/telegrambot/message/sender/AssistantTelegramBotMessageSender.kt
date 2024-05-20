package org.gnori.bgauassistantbot.assistant.telegrambot.message.sender

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.extensions.api.send.media.*
import dev.inmo.tgbotapi.extensions.api.send.send
import dev.inmo.tgbotapi.requests.abstracts.asMultipartFile
import dev.inmo.tgbotapi.types.ChatId
import dev.inmo.tgbotapi.types.RawChatId
import dev.inmo.tgbotapi.types.media.TelegramMedia
import dev.inmo.tgbotapi.types.message.HTMLParseMode
import dev.inmo.tgbotapi.types.message.MarkdownParseMode
import dev.inmo.tgbotapi.types.message.content.*
import kotlinx.coroutines.reactor.mono
import org.gnori.bgauassistantbot.assistant.telegrambot.AssistantTelegramBotData
import org.gnori.bgauassistantbot.assistant.telegrambot.message.sender.model.DocumentMedia
import org.gnori.bgauassistantbot.assistant.telegrambot.message.sender.model.Message
import org.gnori.bgauassistantbot.assistant.telegrambot.message.sender.model.ParseMode
import org.gnori.bgauassistantbot.assistant.telegrambot.message.sender.model.VisualMedia
import org.gnori.bgauassistantbot.common.telegrambot.message.sender.TelegramBotMessageSender
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import java.io.File

@Component
class AssistantTelegramBotMessageSender(
    botData: AssistantTelegramBotData,
    private val visualMediaSender: TelegramBotMessageSender<VisualMedia>,
    private val documentMediaSender: TelegramBotMessageSender<DocumentMedia>
) : TelegramBotMessageSender<Message> {

    private val telegramBot: TelegramBot = botData.telegramBot

    override fun send(params: Message): Mono<Boolean> =
        Mono.defer {
            val chatId = ChatId(RawChatId(params.chatId))
            val parseMode = when (params.parseMode) {
                ParseMode.HTML -> HTMLParseMode
                ParseMode.MARKDOWN -> MarkdownParseMode
                ParseMode.NULL -> null
            }

            mono {
                telegramBot.send(
                    chatId = chatId,
                    parseMode = parseMode,
                    text = params.text,
                    replyMarkup = params.keyboardMarkup
                ).let { true }
            }.flatMap { isSended ->
                visualMediaSender.send(params.visualMedia)
            }.flatMap { isSended ->
                documentMediaSender.send(params.documentMedia)
            }
        }
}
