package org.gnori.elasticbot.telegram.message.sender.model

import dev.inmo.tgbotapi.requests.abstracts.InputFile

sealed interface Media {
    val file: InputFile
}