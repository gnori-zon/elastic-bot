package org.gnori.elasticbot.common.telegrambot.message.preparer

interface TelegramBotMessagePreparer<P, R> {
    fun prepare(param: P): R
}