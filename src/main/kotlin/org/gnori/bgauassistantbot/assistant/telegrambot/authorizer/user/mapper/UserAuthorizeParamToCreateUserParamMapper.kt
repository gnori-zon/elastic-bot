package org.gnori.bgauassistantbot.assistant.telegrambot.authorizer.user.mapper

import org.gnori.bgauassistantbot.assistant.telegrambot.authorizer.user.model.UserAuthorizeParam
import org.gnori.bgauassistantbot.common.mapper.Mapper
import org.gnori.bgauassistantbot.common.user.model.create.CreateUserParam
import org.springframework.stereotype.Component

@Component
class UserAuthorizeParamToCreateUserParamMapper : Mapper<UserAuthorizeParam, CreateUserParam> {

    override fun map(param: UserAuthorizeParam): CreateUserParam =
        CreateUserParam(
            username = param.username,
            chatId = param.chatId
        )
}