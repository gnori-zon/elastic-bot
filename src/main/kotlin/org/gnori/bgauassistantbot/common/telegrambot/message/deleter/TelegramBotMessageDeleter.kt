package org.gnori.bgauassistantbot.common.telegrambot.message.deleter

interface TelegramBotMessageDeleter<P> {
    fun delete(param: P): Boolean
}