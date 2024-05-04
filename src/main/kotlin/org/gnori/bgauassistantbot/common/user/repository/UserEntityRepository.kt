package org.gnori.bgauassistantbot.common.user.repository

import org.gnori.bgauassistantbot.common.user.entity.UserEntity
import org.springframework.data.r2dbc.repository.R2dbcRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono
import java.util.*

@Repository
interface UserEntityRepository: R2dbcRepository<UserEntity, UUID> {

    fun findByChatId(chatId: Long): Mono<UserEntity>
}