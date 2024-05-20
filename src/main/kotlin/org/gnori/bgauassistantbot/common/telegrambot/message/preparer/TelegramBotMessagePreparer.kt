package org.gnori.bgauassistantbot.common.telegrambot.message.preparer

import reactor.core.publisher.Mono

interface TelegramBotMessagePreparer<P, R> {
    fun prepare(param: P): Mono<R>
}