package org.gnori.elasticbot.telegram.mapper

import dev.inmo.tgbotapi.extensions.utils.extensions.raw.data
import dev.inmo.tgbotapi.extensions.utils.extensions.raw.message
import dev.inmo.tgbotapi.types.update.CallbackQueryUpdate
import org.gnori.elasticbot.telegram.ext.username
import org.gnori.elasticbot.telegram.update.processor.model.CallbackEvent
import org.gnori.elasticbot.telegram.update.processor.model.Event

fun CallbackQueryUpdate.toCallbackEvent(): Event? {
    val message = data.message ?: return null
    return CallbackEvent(
        this.updateId.long,
        message.messageId.long,
        message.chat.id.chatId.long,
        this.username,
        this.data.data
    )
}