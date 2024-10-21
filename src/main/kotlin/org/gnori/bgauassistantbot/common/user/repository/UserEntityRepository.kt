package org.gnori.bgauassistantbot.common.user.repository

import org.gnori.bgauassistantbot.common.user.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserEntityRepository: JpaRepository<UserEntity, UUID> {
    fun findByChatId(chatId: Long): UserEntity?
}