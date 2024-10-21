package org.gnori.bgauassistantbot.common.user.service

import org.gnori.bgauassistantbot.common.user.mapper.CreateUserParamToEntityMapper
import org.gnori.bgauassistantbot.common.user.mapper.UserEntityToUserMapper
import org.gnori.bgauassistantbot.common.user.model.User
import org.gnori.bgauassistantbot.common.user.model.create.CreateUserParam
import org.gnori.bgauassistantbot.common.user.repository.UserEntityRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserServiceImpl(
    private val repository: UserEntityRepository,
    private val entityToModelMapper: UserEntityToUserMapper,
    private val createUserToEntityMapper: CreateUserParamToEntityMapper
): UserService {

    override fun findByChatId(chatId: Long): User? =
        repository.findByChatId(chatId)
            ?.let(entityToModelMapper::map)

    override fun findById(id: String): User? =
        repository.findById(UUID.fromString(id)).orElse(null)
            ?.let(entityToModelMapper::map)

    override fun create(param: CreateUserParam): User =
        createUserToEntityMapper.map(param)
            .let(repository::saveAndFlush)
            .let(entityToModelMapper::map)
}