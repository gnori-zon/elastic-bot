package org.gnori.bgauassistantbot.common.telegrambot.update.consumer

interface TelegramBotConsumer {
    val name: String
    fun startConsuming()
}