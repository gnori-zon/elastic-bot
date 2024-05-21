package org.gnori.bgauassistantbot.assistant.telegrambot.message.sender.model.sending

import dev.inmo.tgbotapi.requests.abstracts.InputFile
import dev.inmo.tgbotapi.types.media.DocumentMediaGroupMemberTelegramMedia
import dev.inmo.tgbotapi.types.media.TelegramMediaDocument
import org.gnori.bgauassistantbot.assistant.telegrambot.message.sender.model.Document

class DocumentMediaGroup(
    val chatId: Long,
    val documents: List<Document>
) {
    companion object {
        operator fun invoke(chatId: Long, documents: List<InputFile>): DocumentMediaGroup =
            DocumentMediaGroup(chatId, documents.map { Document(it) })
    }

    fun documentsMediaContents(): List<DocumentMediaGroupMemberTelegramMedia> =
        documents.map { TelegramMediaDocument(it.file) }
}