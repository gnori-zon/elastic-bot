package org.gnori.bgauassistantbot.common.user.service

import org.gnori.bgauassistantbot.common.user.model.User
import org.gnori.bgauassistantbot.common.user.model.create.CreateUserParam
import reactor.core.publisher.Mono

interface UserService {

    fun findByChatId(chatId: Long): Mono<User>
    fun create(param: CreateUserParam): Mono<User>
}