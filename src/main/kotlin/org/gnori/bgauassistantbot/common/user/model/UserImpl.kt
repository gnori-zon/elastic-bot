package org.gnori.bgauassistantbot.common.user.model

class UserImpl(
    override val id: String,
    override val username: String,
    override val chatId: Long
) : User