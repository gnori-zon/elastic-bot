package org.gnori.elasticbot.telegrambot.message.sender.model.sending

import dev.inmo.tgbotapi.requests.abstracts.InputFile
import dev.inmo.tgbotapi.types.media.TelegramMediaPhoto
import dev.inmo.tgbotapi.types.media.TelegramMediaVideo
import dev.inmo.tgbotapi.types.media.VisualMediaGroupMemberTelegramMedia
import org.gnori.elasticbot.telegrambot.message.sender.model.Photo
import org.gnori.elasticbot.telegrambot.message.sender.model.Video

class VisualMediaGroup(
    val chatId: Long,
    val photos: List<Photo> = emptyList(),
    val videos: List<Video> = emptyList()
) {
    companion object {
        operator fun invoke(chatId: Long, photos: List<InputFile>, videos: List<InputFile>): VisualMediaGroup =
            VisualMediaGroup(chatId, photos.map { Photo(it) }, videos.map { Video(it) })
    }

    fun visualMediaContents(): List<VisualMediaGroupMemberTelegramMedia> =
        photos.map { TelegramMediaPhoto(it.file) }
            .plus(videos.map { TelegramMediaVideo(it.file) })

    fun visualMediaContentsSize(): Int = photos.size + videos.size
}