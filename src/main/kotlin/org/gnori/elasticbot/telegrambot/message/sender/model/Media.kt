package org.gnori.elasticbot.telegrambot.message.sender.model

import dev.inmo.tgbotapi.requests.abstracts.InputFile

sealed interface Media {
    val file: InputFile
}