package org.gnori.elasticbot.telegram.mapper

import dev.inmo.tgbotapi.types.update.CallbackQueryUpdate
import dev.inmo.tgbotapi.types.update.MessageUpdate
import dev.inmo.tgbotapi.types.update.abstracts.Update
import org.gnori.elasticbot.telegram.update.processor.model.Event

fun Update.toEvent(): Event? {
    return when (this) {
        is CallbackQueryUpdate -> this.toCallbackEvent()
        is MessageUpdate -> this.toTextEvent()
        else -> null
    }
}