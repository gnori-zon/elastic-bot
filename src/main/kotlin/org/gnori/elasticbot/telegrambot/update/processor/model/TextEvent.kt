package org.gnori.elasticbot.telegrambot.update.processor.model

class TextEvent(
    override val updateId: Long,
    override val messageId: Long,
    override val chatId: Long,
    override val username: String,
    val text: String
) : Event