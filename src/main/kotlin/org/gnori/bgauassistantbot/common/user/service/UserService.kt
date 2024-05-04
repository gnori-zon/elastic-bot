package org.gnori.bgauassistantbot.common.user.service

import org.gnori.bgauassistantbot.common.user.model.User
import reactor.core.publisher.Mono

interface UserService {

    fun findByChatId(chatId: Long): Mono<User>
}