package org.gnori.elasticbot.common.telegrambot.message.deleter

interface TelegramBotMessageDeleter<P> {
    fun delete(param: P): Boolean
}