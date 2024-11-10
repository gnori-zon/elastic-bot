package org.gnori.elasticbot.common.user.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.*

@Entity
@Table(name = "tg_users")
class UserEntity(
    @Id var id: UUID? = null,
    var username: String,
    var chatId: Long
) {
    constructor() : this(username = "", chatId = -1L)
}