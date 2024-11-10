package org.gnori.elasticbot.common.user.mapper

import org.gnori.elasticbot.common.mapper.Mapper
import org.gnori.elasticbot.common.user.entity.UserEntity
import org.gnori.elasticbot.common.user.model.User
import org.gnori.elasticbot.common.user.model.UserImpl
import org.springframework.stereotype.Component

@Component
class UserEntityToUserMapper: Mapper<UserEntity, User> {

    override fun map(param: UserEntity): User =
        UserImpl(
            id = param.id.toString(),
            username = param.username,
            chatId = param.chatId
        )
}