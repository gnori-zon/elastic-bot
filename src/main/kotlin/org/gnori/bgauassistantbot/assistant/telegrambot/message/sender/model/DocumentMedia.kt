package org.gnori.bgauassistantbot.assistant.telegrambot.message.sender.model

import dev.inmo.tgbotapi.requests.abstracts.InputFile
import dev.inmo.tgbotapi.types.media.DocumentMediaGroupMemberTelegramMedia
import dev.inmo.tgbotapi.types.media.TelegramMediaDocument

class DocumentMedia(
    val chatId: Long,
    val documents: List<InputFile>
) {
    fun documentsMediaContents(): List<DocumentMediaGroupMemberTelegramMedia> =
        documents.map { TelegramMediaDocument(it) }
}