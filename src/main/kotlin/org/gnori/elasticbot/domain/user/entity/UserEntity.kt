package org.gnori.elasticbot.domain.user.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.UUID

@Entity
@Table(name = "_users")
class UserEntity(
    @Id
    val id: UUID? = null,
    val name: String? = null,
    val createdAt: Long? = null,
    val updatedAt: Long? = null
)