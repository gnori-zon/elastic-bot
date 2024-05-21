package org.gnori.bgauassistantbot.assistant.telegrambot.message.sender.model.sending

import dev.inmo.tgbotapi.requests.abstracts.InputFile
import dev.inmo.tgbotapi.types.buttons.KeyboardMarkup
import org.gnori.bgauassistantbot.assistant.telegrambot.message.sender.model.Media
import org.gnori.bgauassistantbot.assistant.telegrambot.message.sender.model.ParseMode
import org.gnori.bgauassistantbot.assistant.telegrambot.message.sender.model.Text

class Message(
    chatId: Long,
    text: String,
    headerMedia: Media?,
    parseMode: ParseMode = ParseMode.NULL,
    keyboardMarkup: KeyboardMarkup? = null,
    photos: List<InputFile> = emptyList(),
    videos: List<InputFile> = emptyList(),
    documents: List<InputFile> = emptyList()
) {

    private val text = Text(text, parseMode, keyboardMarkup)
    val textWithChatId = TextWithChatId(chatId, this.text)
    val visualMedia = VisualMediaGroup(chatId, photos, videos)
    val documentMedia = DocumentMediaGroup(chatId, documents)
    val textWithMedia = headerMedia?.let { TextWithMedia(chatId, this.text, it) }
}