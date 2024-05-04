package org.gnori.bgauassistantbot.common.user.mapper

import org.gnori.bgauassistantbot.common.mapper.Mapper
import org.gnori.bgauassistantbot.common.user.entity.UserEntity
import org.gnori.bgauassistantbot.common.user.model.create.CreateUserParam
import org.springframework.stereotype.Component

@Component
class CreateUserParamToEntityMapper : Mapper<CreateUserParam, UserEntity> {

    override fun map(param: CreateUserParam): UserEntity =
        UserEntity(
            username = param.username,
            chatId = param.chatId
        )
}