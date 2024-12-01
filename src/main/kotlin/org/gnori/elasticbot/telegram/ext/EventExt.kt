package org.gnori.elasticbot.telegram.ext

import org.gnori.elasticbot.telegram.update.processor.model.CallbackEvent
import org.gnori.elasticbot.telegram.update.processor.model.Event

val Event.callbackData: String?
    get() {
        return when (this) {
            is CallbackEvent -> data
            else -> null
        }
    }