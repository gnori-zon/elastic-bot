package org.gnori.elasticbot.telegram.mapper

import dev.inmo.tgbotapi.extensions.utils.extensions.raw.text
import dev.inmo.tgbotapi.types.update.MessageUpdate
import org.gnori.elasticbot.telegram.ext.username
import org.gnori.elasticbot.telegram.update.processor.model.TextEvent

fun MessageUpdate.toTextEvent(): TextEvent {
    return TextEvent(
        this.updateId.long,
        this.data.messageId.long,
        this.data.chat.id.chatId.long,
        this.username,
        this.data.text
    )
}