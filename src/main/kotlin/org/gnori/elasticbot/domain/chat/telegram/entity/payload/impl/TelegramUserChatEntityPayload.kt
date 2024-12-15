package org.gnori.elasticbot.domain.chat.telegram.entity.payload.impl

import org.gnori.elasticbot.domain.chat.telegram.entity.payload.TelegramChatEntityPayload
import org.gnori.elasticbot.domain.chat.telegram.entity.payload.TelegramChatEntityPayloadType
import java.util.UUID

class TelegramUserChatEntityPayload(val userId: UUID) : TelegramChatEntityPayload {
    override val type = TelegramChatEntityPayloadType.USER
}