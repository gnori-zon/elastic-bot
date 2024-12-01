package org.gnori.elasticbot.telegram.message.sender.model.sending

import org.gnori.elasticbot.telegram.message.sender.model.Media
import org.gnori.elasticbot.telegram.message.sender.model.Text

data class TextWithMedia (
    val chatId: Long,
    val text: Text,
    val media: Media
)