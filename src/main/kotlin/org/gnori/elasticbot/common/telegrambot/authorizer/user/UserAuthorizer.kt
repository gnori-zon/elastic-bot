package org.gnori.elasticbot.common.telegrambot.authorizer.user

import org.gnori.elasticbot.common.user.model.User

interface UserAuthorizer<P> {
    fun authorize(param: P): User
}