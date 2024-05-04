package org.gnori.bgauassistantbot.common.user.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.util.*

@Table(name = "tg_user")
class UserEntity(
    @Id val id: UUID? = null,
    val username: String,
    val chatId: Long
)