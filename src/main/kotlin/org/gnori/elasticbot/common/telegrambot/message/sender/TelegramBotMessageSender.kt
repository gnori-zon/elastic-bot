package org.gnori.elasticbot.common.telegrambot.message.sender

interface TelegramBotMessageSender<P> {
    fun send(params: P): Boolean
}