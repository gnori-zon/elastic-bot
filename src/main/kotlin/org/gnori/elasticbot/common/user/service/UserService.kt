package org.gnori.elasticbot.common.user.service

import org.gnori.elasticbot.common.user.model.User
import org.gnori.elasticbot.common.user.model.create.CreateUserParams

interface UserService {

    fun findByChatId(chatId: Long): User?
    fun findById(id: String): User?
    fun create(param: CreateUserParams): User
}