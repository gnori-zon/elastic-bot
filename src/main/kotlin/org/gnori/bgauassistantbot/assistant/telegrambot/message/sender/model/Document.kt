package org.gnori.bgauassistantbot.assistant.telegrambot.message.sender.model

import dev.inmo.tgbotapi.requests.abstracts.InputFile

data class Document(
    override val file: InputFile
): Media