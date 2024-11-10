package org.gnori.elasticbot.common.ext

import dev.inmo.tgbotapi.types.message.HTMLParseMode
import dev.inmo.tgbotapi.types.message.MarkdownParseMode
import org.gnori.elasticbot.telegrambot.message.sender.model.ParseMode

fun ParseMode.toInmoParseMode(): dev.inmo.tgbotapi.types.message.ParseMode? {

    return when (this) {
        ParseMode.HTML -> HTMLParseMode
        ParseMode.MARKDOWN -> MarkdownParseMode
        ParseMode.NULL -> null
    }
}