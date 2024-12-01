package org.gnori.elasticbot.common.telegrambot.update.processor

import org.gnori.elasticbot.telegram.update.processor.model.Event

interface TelegramBotUpdateProcessor {
    fun process(event: Event)
}