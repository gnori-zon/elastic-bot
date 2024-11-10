package org.gnori.elasticbot.common.telegrambot.update.consumer

interface TelegramBotConsumer {
    val name: String
    fun startConsuming()
}