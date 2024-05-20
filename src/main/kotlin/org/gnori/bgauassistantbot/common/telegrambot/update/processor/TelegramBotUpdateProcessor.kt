package org.gnori.bgauassistantbot.common.telegrambot.update.processor

import dev.inmo.tgbotapi.types.update.abstracts.Update

interface TelegramBotUpdateProcessor {
    fun process(update: Update)
}