package org.gnori.elasticbot.telegram.message.sender.model.sending

import org.gnori.elasticbot.telegram.message.sender.model.Text

data class TextWithChatId(
    val chatId: Long,
    val text: Text
)