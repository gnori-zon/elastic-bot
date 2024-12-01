package org.gnori.elasticbot.telegram.authorizer.user

import org.gnori.elasticbot.common.telegrambot.authorizer.user.UserAuthorizer
import org.gnori.elasticbot.common.user.model.User
import org.gnori.elasticbot.common.user.service.UserService
import org.gnori.elasticbot.telegram.authorizer.user.model.UserAuthorizationParams
import org.gnori.elasticbot.telegram.mapper.toCreateUserParams
import org.springframework.stereotype.Component

@Component
class UserAuthorizerImpl(
    private val userService: UserService
) : UserAuthorizer<UserAuthorizationParams> {

    override fun authorize(param: UserAuthorizationParams): User {
        return userService.findByChatId(param.chatId)
            ?: userService.create(param.toCreateUserParams())
    }
}