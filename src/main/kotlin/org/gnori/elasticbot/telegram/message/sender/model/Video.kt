package org.gnori.elasticbot.telegram.message.sender.model

import dev.inmo.tgbotapi.requests.abstracts.InputFile

data class Video(
    override val file: InputFile
): Media