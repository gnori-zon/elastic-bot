package org.gnori.elasticbot.telegrambot.update.processor.model

interface Event {
    val updateId: Long
    val messageId: Long
    val chatId: Long
    val username: String
}