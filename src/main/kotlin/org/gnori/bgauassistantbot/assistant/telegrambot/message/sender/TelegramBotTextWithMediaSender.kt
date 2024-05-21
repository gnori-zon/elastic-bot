package org.gnori.bgauassistantbot.assistant.telegrambot.message.sender

import dev.inmo.tgbotapi.extensions.api.send.media.sendDocument
import dev.inmo.tgbotapi.extensions.api.send.media.sendPhoto
import dev.inmo.tgbotapi.extensions.api.send.media.sendVideo
import kotlinx.coroutines.reactor.mono
import org.gnori.bgauassistantbot.assistant.telegrambot.AssistantTelegramBotData
import org.gnori.bgauassistantbot.assistant.telegrambot.message.sender.model.Document
import org.gnori.bgauassistantbot.assistant.telegrambot.message.sender.model.Photo
import org.gnori.bgauassistantbot.assistant.telegrambot.message.sender.model.Video
import org.gnori.bgauassistantbot.assistant.telegrambot.message.sender.model.sending.TextWithMedia
import org.gnori.bgauassistantbot.common.ext.toChatId
import org.gnori.bgauassistantbot.common.ext.toInmoParseMode
import org.gnori.bgauassistantbot.common.telegrambot.message.sender.TelegramBotMessageSender
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class TelegramBotTextWithMediaSender(
    botData: AssistantTelegramBotData
) : TelegramBotMessageSender<TextWithMedia> {

    private val telegramBot = botData.telegramBot

    override fun send(params: TextWithMedia): Mono<Boolean> {

        return mono {
            when (params.media) {
                is Document -> sendDocument(params)
                is Photo -> sendPhoto(params)
                is Video -> sendVideo(params)
            }
        }
    }

    private suspend fun sendDocument(params: TextWithMedia): Boolean {

        return telegramBot.sendDocument(
            chatId = params.chatId.toChatId(),
            document = params.media.file,
            parseMode = params.text.parseMode.toInmoParseMode(),
            text = params.text.text,
            replyMarkup = params.text.keyboardMarkup
        ).let { true }
    }

    private suspend fun sendPhoto(params: TextWithMedia): Boolean {

        return telegramBot.sendPhoto(
            chatId = params.chatId.toChatId(),
            fileId = params.media.file,
            parseMode = params.text.parseMode.toInmoParseMode(),
            text = params.text.text,
            replyMarkup = params.text.keyboardMarkup
        ).let { true }
    }

    private suspend fun sendVideo(params: TextWithMedia): Boolean {

        return telegramBot.sendVideo(
            chatId = params.chatId.toChatId(),
            video = params.media.file,
            parseMode = params.text.parseMode.toInmoParseMode(),
            text = params.text.text,
            replyMarkup = params.text.keyboardMarkup
        ).let { true }
    }
}