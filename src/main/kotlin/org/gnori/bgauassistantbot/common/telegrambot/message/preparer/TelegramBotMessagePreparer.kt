package org.gnori.bgauassistantbot.common.telegrambot.message.preparer

interface TelegramBotMessagePreparer<P, R> {
    fun prepare(param: P): R
}