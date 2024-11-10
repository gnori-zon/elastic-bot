package org.gnori.elasticbot.telegrambot.message.sender

import dev.inmo.tgbotapi.extensions.api.send.media.sendDocument
import dev.inmo.tgbotapi.extensions.api.send.media.sendDocumentsGroup
import dev.inmo.tgbotapi.extensions.api.send.media.sendPhoto
import dev.inmo.tgbotapi.extensions.api.send.media.sendVideo
import kotlinx.coroutines.runBlocking
import org.gnori.elasticbot.telegrambot.TelegramBotKeeper
import org.gnori.elasticbot.telegrambot.message.sender.model.Document
import org.gnori.elasticbot.telegrambot.message.sender.model.Photo
import org.gnori.elasticbot.telegrambot.message.sender.model.Video
import org.gnori.elasticbot.telegrambot.message.sender.model.sending.TextWithMedia
import org.gnori.elasticbot.common.ext.toChatId
import org.gnori.elasticbot.common.ext.toInmoParseMode
import org.gnori.elasticbot.common.telegrambot.message.sender.TelegramBotMessageSender
import org.springframework.stereotype.Component

@Component
class TelegramBotTextWithMediaSender(
    botKeeper: TelegramBotKeeper
) : TelegramBotMessageSender<TextWithMedia> {

    private val bot = botKeeper.bot

    override fun send(params: TextWithMedia): Boolean {

        return runBlocking {
            when (params.media) {
                is Document -> sendDocument(params)
                is Photo -> sendPhoto(params)
                is Video -> sendVideo(params)
            }
        }
    }

    private suspend fun sendDocument(params: TextWithMedia): Boolean {

        return bot.sendDocument(
            chatId = params.chatId.toChatId(),
            document = params.media.file,
            parseMode = params.text.parseMode.toInmoParseMode(),
            text = params.text.text,
            replyMarkup = params.text.keyboardMarkup
        ).let { true }
    }

    private suspend fun sendPhoto(params: TextWithMedia): Boolean {

        return bot.sendPhoto(
            chatId = params.chatId.toChatId(),
            fileId = params.media.file,
            parseMode = params.text.parseMode.toInmoParseMode(),
            text = params.text.text,
            replyMarkup = params.text.keyboardMarkup
        ).let { true }
    }

    private suspend fun sendVideo(params: TextWithMedia): Boolean {

        return bot.sendVideo(
            chatId = params.chatId.toChatId(),
            video = params.media.file,
            parseMode = params.text.parseMode.toInmoParseMode(),
            text = params.text.text,
            replyMarkup = params.text.keyboardMarkup
        ).let { true }
    }
}