package org.gnori.bgauassistantbot.common.telegrambot

import dev.inmo.tgbotapi.types.update.abstracts.Update

interface TelegramBot {

    val name: String

    fun startConsuming()

    fun consume(update: Update)
}