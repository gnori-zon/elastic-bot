package org.gnori.elasticbot.telegram.ext

import dev.inmo.tgbotapi.types.MessageId

fun MessageId.Companion.from(id: Long): MessageId {
    return MessageId(id)
}