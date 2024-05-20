package org.gnori.bgauassistantbot.common.telegrambot.message.deleter

import reactor.core.publisher.Mono

interface TelegramBotMessageDeleter<P> {
    fun delete(param: P): Mono<Boolean>
}