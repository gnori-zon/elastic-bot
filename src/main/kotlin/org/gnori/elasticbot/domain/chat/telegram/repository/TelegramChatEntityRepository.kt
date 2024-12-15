package org.gnori.elasticbot.domain.chat.telegram.repository

import org.gnori.elasticbot.domain.chat.telegram.entity.TelegramChatEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface TelegramChatEntityRepository : JpaRepository<TelegramChatEntity, UUID>