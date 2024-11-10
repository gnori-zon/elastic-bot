package org.gnori.elasticbot.common.telegrambot.update.processor

import org.gnori.elasticbot.telegrambot.update.processor.model.Event

interface TelegramBotUpdateProcessor {
    fun process(event: Event)
}