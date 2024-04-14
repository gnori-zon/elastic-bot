package org.gnori.bgauassistantbot.common.telegrambot.consumer

import dev.inmo.tgbotapi.types.update.abstracts.Update

interface TelegramBotConsumer {
    val name: String
    fun startConsuming()
    fun consume(update: Update)
}