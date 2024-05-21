package org.gnori.bgauassistantbot.assistant.telegrambot.message.sender

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.extensions.api.send.media.sendDocument
import dev.inmo.tgbotapi.extensions.api.send.media.sendPhoto
import dev.inmo.tgbotapi.extensions.api.send.media.sendVideo
import dev.inmo.tgbotapi.extensions.api.send.send
import dev.inmo.tgbotapi.types.ChatId
import dev.inmo.tgbotapi.types.RawChatId
import dev.inmo.tgbotapi.types.message.HTMLParseMode
import dev.inmo.tgbotapi.types.message.MarkdownParseMode
import kotlinx.coroutines.reactor.mono
import org.gnori.bgauassistantbot.assistant.telegrambot.AssistantTelegramBotData
import org.gnori.bgauassistantbot.assistant.telegrambot.message.sender.model.*
import org.gnori.bgauassistantbot.assistant.telegrambot.message.sender.model.sending.*
import org.gnori.bgauassistantbot.common.telegrambot.message.sender.TelegramBotMessageSender
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class AssistantTelegramBotMessageSender(
    private val visualMediaGroupSender: TelegramBotMessageSender<VisualMediaGroup>,
    private val textSender: TelegramBotMessageSender<TextWithChatId>,
    private val textWithMediaSender: TelegramBotMessageSender<TextWithMedia>,
    private val documentMediaGroupSender: TelegramBotMessageSender<DocumentMediaGroup>
) : TelegramBotMessageSender<Message> {

    override fun send(params: Message): Mono<Boolean> =
        Mono.defer {

            when (params.textWithMedia) {
                null -> textSender.send(params.textWithChatId)
                else -> textWithMediaSender.send(params.textWithMedia)
            }.flatMap { isSended ->
                visualMediaGroupSender.send(params.visualMedia)
            }.flatMap { isSended ->
                documentMediaGroupSender.send(params.documentMedia)
            }
        }
}
