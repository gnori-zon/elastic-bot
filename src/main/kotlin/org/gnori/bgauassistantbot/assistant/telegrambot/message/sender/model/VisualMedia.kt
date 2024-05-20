package org.gnori.bgauassistantbot.assistant.telegrambot.message.sender.model

import dev.inmo.tgbotapi.requests.abstracts.InputFile
import dev.inmo.tgbotapi.types.media.TelegramMediaPhoto
import dev.inmo.tgbotapi.types.media.TelegramMediaVideo
import dev.inmo.tgbotapi.types.media.VisualMediaGroupMemberTelegramMedia

class VisualMedia(
    val chatId: Long,
    val photos: List<InputFile> = emptyList(),
    val videos: List<InputFile> = emptyList()
) {
    fun visualMediaContents(): List<VisualMediaGroupMemberTelegramMedia> =
        photos.map { TelegramMediaPhoto(it) }
            .plus(videos.map { TelegramMediaVideo(it) })

    fun visualMediaContentsSize(): Int = photos.size + videos.size
}