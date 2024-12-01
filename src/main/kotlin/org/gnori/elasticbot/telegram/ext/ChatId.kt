package org.gnori.elasticbot.telegram.ext

import dev.inmo.tgbotapi.types.ChatId
import dev.inmo.tgbotapi.types.RawChatId

fun ChatId.Companion.from(id: Long): ChatId {
    return ChatId(RawChatId(id))
}