package org.gnori.bgauassistantbot.common.telegrambot.update.consumer

import dev.inmo.tgbotapi.types.update.abstracts.Update
import reactor.core.publisher.Flux

interface TelegramBotConsumer {
    val name: String
    val updates: Flux<Update>
    fun startConsuming()
}