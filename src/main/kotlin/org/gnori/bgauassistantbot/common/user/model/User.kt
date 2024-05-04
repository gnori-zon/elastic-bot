package org.gnori.bgauassistantbot.common.user.model

interface User {
    val id: String
    val username: String
    val chatId: Long
}