package org.gnori.elasticbot.telegram.message.sender

import dev.inmo.tgbotapi.extensions.api.send.media.sendDocument
import dev.inmo.tgbotapi.extensions.api.send.media.sendDocumentsGroup
import dev.inmo.tgbotapi.types.ChatId
import dev.inmo.tgbotapi.types.RawChatId
import kotlinx.coroutines.runBlocking
import org.gnori.elasticbot.telegram.TelegramBotKeeper
import org.gnori.elasticbot.telegram.message.sender.model.sending.DocumentMediaGroup
import org.gnori.elasticbot.common.telegrambot.message.sender.TelegramBotMessageSender
import org.springframework.stereotype.Component

@Component
class TelegramBotDocumentMediaSender(
    botKeeper: TelegramBotKeeper
) : TelegramBotMessageSender<DocumentMediaGroup> {

    private val maxSizeGroupDocumentMedia = 10
    private val bot = botKeeper.bot

    override fun send(params: DocumentMediaGroup): Boolean {

        val chatId = ChatId(RawChatId(params.chatId))

        return runBlocking {
            when {
                params.documents.size > 1 -> {
                    params.documentsMediaContents()
                        .chunked(maxSizeGroupDocumentMedia)
                        .forEach { documentsMediaContents ->
                            bot.sendDocumentsGroup(chatId = chatId, media = documentsMediaContents)
                        }
                        .let { true }
                }

                params.documents.size == 1 ->
                    bot.sendDocument(chatId = chatId, document = params.documents[0].file)
                        .let { true }

                else -> false
            }
        }
    }
}
