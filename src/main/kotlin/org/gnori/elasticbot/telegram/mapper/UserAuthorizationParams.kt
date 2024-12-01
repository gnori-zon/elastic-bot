package org.gnori.elasticbot.telegram.mapper

import org.gnori.elasticbot.common.user.model.create.CreateUserParams
import org.gnori.elasticbot.telegram.authorizer.user.model.UserAuthorizationParams

fun UserAuthorizationParams.toCreateUserParams(): CreateUserParams {
    return CreateUserParams(username, chatId)
}