package org.gnori.bgauassistantbot.assistant.telegrambot.message.sender.model.sending

import org.gnori.bgauassistantbot.assistant.telegrambot.message.sender.model.Text

data class TextWithChatId(
    val chatId: Long,
    val text: Text
)