package org.gnori.bgauassistantbot.assistant.telegrambot.message.sender.model.sending

import org.gnori.bgauassistantbot.assistant.telegrambot.message.sender.model.Media
import org.gnori.bgauassistantbot.assistant.telegrambot.message.sender.model.Text

data class TextWithMedia (
    val chatId: Long,
    val text: Text,
    val media: Media
)