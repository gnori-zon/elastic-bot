package org.gnori.bgauassistantbot.common.telegrambot.sender

interface TelegramBotSender<P> {
    fun send(params: P): Long
}