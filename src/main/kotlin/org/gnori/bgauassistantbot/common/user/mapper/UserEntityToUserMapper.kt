package org.gnori.bgauassistantbot.common.user.mapper

import org.gnori.bgauassistantbot.common.mapper.Mapper
import org.gnori.bgauassistantbot.common.user.entity.UserEntity
import org.gnori.bgauassistantbot.common.user.model.User
import org.gnori.bgauassistantbot.common.user.model.UserImpl
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