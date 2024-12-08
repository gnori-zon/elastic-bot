package org.gnori.elasticbot.domain.content.text.repository

import org.gnori.elasticbot.domain.content.text.entity.TextContentEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface TextContentEntityRepository : JpaRepository<TextContentEntity, UUID>