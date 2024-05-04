package org.gnori.bgauassistantbot.common.user.service

import org.gnori.bgauassistantbot.common.user.mapper.UserEntityToUserMapper
import org.gnori.bgauassistantbot.common.user.model.User
import org.gnori.bgauassistantbot.common.user.repository.UserEntityRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class UserServiceImpl(
    private val repository: UserEntityRepository,
    private val entityToModelMapper: UserEntityToUserMapper
): UserService {

    override fun findByChatId(chatId: Long): Mono<User> =
        repository.findByChatId(chatId)
            .map(entityToModelMapper::map)
}