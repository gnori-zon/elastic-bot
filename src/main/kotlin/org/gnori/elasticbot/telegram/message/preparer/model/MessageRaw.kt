package org.gnori.elasticbot.telegram.message.preparer.model

import org.gnori.elasticbot.common.user.model.User

class MessageRaw(
    val chatId: Long,
    val user: User,
    val id: String?,
    val withBack: Boolean = true
)