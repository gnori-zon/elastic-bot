package org.gnori.elasticbot.domain.chat.telegram.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.gnori.elasticbot.domain.chat.telegram.entity.payload.TelegramChatEntityPayload
import org.hibernate.annotations.JdbcTypeCode
import org.hibernate.type.SqlTypes
import java.util.UUID

@Entity
@Table(name = "telegram_chats")
class TelegramChatEntity(
    @Id
    val id: UUID? = null,
    val externalId: Long? = null,
    @JdbcTypeCode(SqlTypes.JSON)
    val payload: TelegramChatEntityPayload? = null
)