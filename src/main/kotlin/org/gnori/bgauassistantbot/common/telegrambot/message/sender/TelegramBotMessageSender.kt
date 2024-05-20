package org.gnori.bgauassistantbot.common.telegrambot.message.sender

import reactor.core.publisher.Mono

interface TelegramBotMessageSender<P> {
    fun send(params: P): Mono<Boolean>
}