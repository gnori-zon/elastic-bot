package org.gnori.bgauassistantbot.common.named.query.repository

import org.gnori.bgauassistantbot.common.named.query.entity.NamedQueryEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface NamedQueryEntityRepository : JpaRepository<NamedQueryEntity, UUID> {
    fun findByName(name: String): NamedQueryEntity?
}