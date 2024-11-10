package org.gnori.elasticbot.common.user.mapper

import org.gnori.elasticbot.common.mapper.Mapper
import org.gnori.elasticbot.common.user.entity.UserEntity
import org.gnori.elasticbot.common.user.model.create.CreateUserParams
import org.springframework.stereotype.Component

@Component
class CreateUserParamsToEntityMapper : Mapper<CreateUserParams, UserEntity> {

    override fun map(param: CreateUserParams): UserEntity =
        UserEntity(
            username = param.username,
            chatId = param.chatId
        )
}