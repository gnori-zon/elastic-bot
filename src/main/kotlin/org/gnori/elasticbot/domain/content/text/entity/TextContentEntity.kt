package org.gnori.elasticbot.domain.content.text.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.gnori.elasticbot.domain.content.text.entity.payload.TextContentEntityPayload
import org.hibernate.annotations.JdbcTypeCode
import org.hibernate.type.SqlTypes
import java.util.UUID

@Entity
@Table(name = "text_contents")
class TextContentEntity(
    @Id
    val id: UUID? = null,
    val format: TextContentEntityFormatType? = null,
    @JdbcTypeCode(SqlTypes.JSON)
    val payload: TextContentEntityPayload? = null,
    val createdAt: Long? = null,
    val updatedAt: Long? = null
)