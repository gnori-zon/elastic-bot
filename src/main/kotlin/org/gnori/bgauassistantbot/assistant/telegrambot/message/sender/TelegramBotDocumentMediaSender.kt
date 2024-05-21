package org.gnori.bgauassistantbot.assistant.telegrambot.message.sender

import dev.inmo.tgbotapi.extensions.api.send.media.sendDocument
import dev.inmo.tgbotapi.extensions.api.send.media.sendDocumentsGroup
import dev.inmo.tgbotapi.types.ChatId
import dev.inmo.tgbotapi.types.RawChatId
import kotlinx.coroutines.reactor.mono
import org.gnori.bgauassistantbot.assistant.telegrambot.AssistantTelegramBotData
import org.gnori.bgauassistantbot.assistant.telegrambot.message.sender.model.sending.DocumentMediaGroup
import org.gnori.bgauassistantbot.common.telegrambot.message.sender.TelegramBotMessageSender
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class TelegramBotDocumentMediaSender(
    botData: AssistantTelegramBotData
) : TelegramBotMessageSender<DocumentMediaGroup> {

    private val maxSizeGroupDocumentMedia = 10
    private val telegramBot = botData.telegramBot

    override fun send(params: DocumentMediaGroup): Mono<Boolean> {

        val chatId = ChatId(RawChatId(params.chatId))

        return Mono.defer {
            mono {

                return@mono when {
                    params.documents.size > 1 -> {
                        params.documentsMediaContents().chunked(maxSizeGroupDocumentMedia)
                            .forEach { documentsMediaContents ->
                                telegramBot.sendDocumentsGroup(chatId = chatId, media = documentsMediaContents)
                            }
                            .let { true }
                    }

                    params.documents.size == 1 ->
                        telegramBot.sendDocument(chatId = chatId, document = params.documents[0].file)
                            .let { true }

                    else -> false
                }
            }
        }
    }
}
