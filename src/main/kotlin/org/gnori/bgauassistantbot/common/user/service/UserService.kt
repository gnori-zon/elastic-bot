package org.gnori.bgauassistantbot.common.user.service

import org.gnori.bgauassistantbot.common.user.model.User
import org.gnori.bgauassistantbot.common.user.model.create.CreateUserParam

interface UserService {

    fun findByChatId(chatId: Long): User?
    fun findById(id: String): User?
    fun create(param: CreateUserParam): User
}