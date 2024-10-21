package org.gnori.bgauassistantbot.common.telegrambot.authorizer.user

import org.gnori.bgauassistantbot.common.user.model.User

interface UserAuthorizer<P> {
    fun authorize(param: P): User
}