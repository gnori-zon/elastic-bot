package org.gnori.elasticbot.common.user.model

interface User {
    val id: String
    val username: String
    val chatId: Long
}