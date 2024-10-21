package org.gnori.bgauassistantbot.common.named.query.repository

import org.gnori.bgauassistantbot.common.named.query.entity.NamedQueryInputParamEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface NamedQueryInputParamEntityRepository : JpaRepository<NamedQueryInputParamEntity, UUID> {
    fun findByNamedQueryId(queryId: UUID): List<NamedQueryInputParamEntity>
    fun findByNamedQueryIdAndName(queryId: UUID, name: String): NamedQueryInputParamEntity?
}