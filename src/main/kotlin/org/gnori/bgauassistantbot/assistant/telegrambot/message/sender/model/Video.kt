package org.gnori.bgauassistantbot.assistant.telegrambot.message.sender.model

import dev.inmo.tgbotapi.requests.abstracts.InputFile

data class Video(
    override val file: InputFile
): Media