package org.gnori.elasticbot.telegram.message.sender.model

import dev.inmo.tgbotapi.types.buttons.KeyboardMarkup

data class Text(
    val text: String,
    val parseMode: ParseMode = ParseMode.NULL,
    val keyboardMarkup: KeyboardMarkup? = null,
)