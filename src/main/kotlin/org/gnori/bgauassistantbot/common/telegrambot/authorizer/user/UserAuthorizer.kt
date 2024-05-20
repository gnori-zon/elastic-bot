package org.gnori.bgauassistantbot.common.telegrambot.authorizer.user

import org.gnori.bgauassistantbot.common.user.model.User
import reactor.core.publisher.Mono

interface UserAuthorizer<P> {
    fun authorize(param: P): Mono<User>
}