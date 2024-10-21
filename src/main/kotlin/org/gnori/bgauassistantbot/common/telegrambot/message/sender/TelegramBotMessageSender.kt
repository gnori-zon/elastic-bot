package org.gnori.bgauassistantbot.common.telegrambot.message.sender

interface TelegramBotMessageSender<P> {
    fun send(params: P): Boolean
}