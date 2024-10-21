package org.gnori.bgauassistantbot.common.named.query.repository

import org.gnori.bgauassistantbot.common.named.query.entity.NamedQueryTypeEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface NamedQueryTypeEntityRepository : JpaRepository<NamedQueryTypeEntity, UUID> {
    fun findByName(name: String): NamedQueryTypeEntity?
}