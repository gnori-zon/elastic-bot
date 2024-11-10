package org.gnori.elasticbot.common.user.service

import org.gnori.elasticbot.common.user.mapper.CreateUserParamsToEntityMapper
import org.gnori.elasticbot.common.user.mapper.UserEntityToUserMapper
import org.gnori.elasticbot.common.user.model.User
import org.gnori.elasticbot.common.user.model.create.CreateUserParams
import org.gnori.elasticbot.common.user.repository.UserEntityRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserServiceImpl(
    private val repository: UserEntityRepository,
    private val entityToModelMapper: UserEntityToUserMapper,
    private val createUserToEntityMapper: CreateUserParamsToEntityMapper
): UserService {

    override fun findByChatId(chatId: Long): User? =
        repository.findByChatId(chatId)
            ?.let(entityToModelMapper::map)

    override fun findById(id: String): User? =
        repository.findById(UUID.fromString(id)).orElse(null)
            ?.let(entityToModelMapper::map)

    override fun create(param: CreateUserParams): User =
        createUserToEntityMapper.map(param)
            .let(repository::saveAndFlush)
            .let(entityToModelMapper::map)
}