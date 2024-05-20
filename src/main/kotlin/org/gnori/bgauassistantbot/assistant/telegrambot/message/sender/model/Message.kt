package org.gnori.bgauassistantbot.assistant.telegrambot.message.sender.model

import dev.inmo.tgbotapi.requests.abstracts.InputFile
import dev.inmo.tgbotapi.types.buttons.KeyboardMarkup
import dev.inmo.tgbotapi.types.media.*

class Message(
    val chatId: Long,
    val text: String,
    val parseMode: ParseMode = ParseMode.NULL,
    val keyboardMarkup: KeyboardMarkup? = null,
    photos: List<InputFile> = emptyList(),
    videos: List<InputFile> = emptyList(),
    documents: List<InputFile> = emptyList()
) {

    val visualMedia = VisualMedia(chatId, photos, videos)
    val documentMedia = DocumentMedia(chatId, documents)
}