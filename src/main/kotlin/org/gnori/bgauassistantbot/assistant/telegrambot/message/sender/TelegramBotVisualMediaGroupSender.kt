package org.gnori.bgauassistantbot.assistant.telegrambot.message.sender

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.extensions.api.send.media.sendPhoto
import dev.inmo.tgbotapi.extensions.api.send.media.sendVideo
import dev.inmo.tgbotapi.extensions.api.send.media.sendVisualMediaGroup
import dev.inmo.tgbotapi.types.ChatId
import dev.inmo.tgbotapi.types.RawChatId
import kotlinx.coroutines.runBlocking
import org.gnori.bgauassistantbot.assistant.telegrambot.AssistantTelegramBotData
import org.gnori.bgauassistantbot.assistant.telegrambot.message.sender.model.sending.VisualMediaGroup
import org.gnori.bgauassistantbot.common.telegrambot.message.sender.TelegramBotMessageSender
import org.springframework.stereotype.Component

@Component
class TelegramBotVisualMediaGroupSender(
    botData: AssistantTelegramBotData
) : TelegramBotMessageSender<VisualMediaGroup> {

    private val maxSizeGroupVisualMedia = 10
    private val telegramBot: TelegramBot = botData.telegramBot

    override fun send(params: VisualMediaGroup): Boolean {

        val chatId = ChatId(RawChatId(params.chatId))

        return runBlocking {
            when {
                params.visualMediaContentsSize() > 1 -> {
                    params.visualMediaContents().chunked(maxSizeGroupVisualMedia)
                        .forEach { visualMediaContents ->
                            telegramBot.sendVisualMediaGroup(chatId = chatId, media = visualMediaContents)
                        }
                        .let { true }
                }

                params.photos.size == 1 ->
                    telegramBot.sendPhoto(chatId = chatId, fileId = params.photos[0].file)
                        .let { true }

                params.videos.size == 1 ->
                    telegramBot.sendVideo(chatId = chatId, video = params.videos[0].file)
                        .let { true }

                else -> false
            }
        }
    }
}