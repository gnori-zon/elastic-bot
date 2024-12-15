package org.gnori.elasticbot.domain.data.url.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.UUID

@Entity
@Table(name = "url_data")
class UrlDataEntity(
    @Id
    val id: UUID? = null,
    val url: String? = null,
    val type: UrlDataEntityType? = null,
    val createdAt: Long? = null,
    val updatedAt: Long? = null
)