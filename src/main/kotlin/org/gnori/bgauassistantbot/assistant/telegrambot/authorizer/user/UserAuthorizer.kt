package org.gnori.bgauassistantbot.assistant.telegrambot.authorizer.user

import org.gnori.bgauassistantbot.assistant.telegrambot.authorizer.user.model.UserAuthorizeParam
import org.gnori.bgauassistantbot.common.user.model.User
import reactor.core.publisher.Mono

interface UserAuthorizer {
    fun authorize(param: UserAuthorizeParam): Mono<User>
}