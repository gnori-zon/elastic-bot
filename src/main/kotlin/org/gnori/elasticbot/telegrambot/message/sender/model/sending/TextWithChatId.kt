package org.gnori.elasticbot.telegrambot.message.sender.model.sending

import org.gnori.elasticbot.telegrambot.message.sender.model.Text

data class TextWithChatId(
    val chatId: Long,
    val text: Text
)