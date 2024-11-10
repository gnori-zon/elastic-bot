package org.gnori.elasticbot.common.named.query.repository

import org.gnori.elasticbot.common.named.query.entity.NamedQueryInputParamTypeEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface NamedQueryInputParamTypeEntityRepository : JpaRepository<NamedQueryInputParamTypeEntity, UUID> {
    fun findByName(name: String): NamedQueryInputParamTypeEntity?
}