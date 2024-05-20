package org.gnori.bgauassistantbot.assistant.telegrambot.message.sender

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.extensions.api.send.media.sendPhoto
import dev.inmo.tgbotapi.extensions.api.send.media.sendVideo
import dev.inmo.tgbotapi.extensions.api.send.media.sendVisualMediaGroup
import dev.inmo.tgbotapi.types.ChatId
import dev.inmo.tgbotapi.types.RawChatId
import kotlinx.coroutines.reactor.mono
import org.gnori.bgauassistantbot.assistant.telegrambot.AssistantTelegramBotData
import org.gnori.bgauassistantbot.assistant.telegrambot.message.sender.model.VisualMedia
import org.gnori.bgauassistantbot.common.telegrambot.message.sender.TelegramBotMessageSender
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class TelegramBotVisualMediaSender(
    botData: AssistantTelegramBotData
) : TelegramBotMessageSender<VisualMedia> {

    private val maxSizeGroupVisualMedia = 10
    private val telegramBot: TelegramBot = botData.telegramBot

    override fun send(params: VisualMedia): Mono<Boolean> {

        val chatId = ChatId(RawChatId(params.chatId))

        return Mono.defer {
            mono {

                return@mono when {
                    params.visualMediaContentsSize() > 1 -> {
                        params.visualMediaContents().chunked(maxSizeGroupVisualMedia)
                            .forEach { visualMediaContents ->
                                telegramBot.sendVisualMediaGroup(chatId = chatId, media = visualMediaContents)
                            }
                            .let { true }
                    }

                    params.photos.size == 1 ->
                        telegramBot.sendPhoto(chatId = chatId, fileId = params.photos[0])
                            .let { true }

                    params.videos.size == 1 ->
                        telegramBot.sendVideo(chatId = chatId, video = params.videos[0])
                            .let { true }

                    else -> false
                }
            }
        }
    }
}