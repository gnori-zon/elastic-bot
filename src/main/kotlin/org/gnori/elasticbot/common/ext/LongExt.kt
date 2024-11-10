package org.gnori.elasticbot.common.ext

import dev.inmo.tgbotapi.types.ChatId
import dev.inmo.tgbotapi.types.RawChatId

fun Long.toChatId(): ChatId {
    return ChatId(RawChatId(this))
}