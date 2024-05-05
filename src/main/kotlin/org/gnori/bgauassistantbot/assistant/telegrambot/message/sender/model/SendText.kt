package org.gnori.bgauassistantbot.assistant.telegrambot.message.sender.model

class SendText(
    val chatId: Long,
    val text: String,
    val parseMode: ParseMode = ParseMode.NULL
)