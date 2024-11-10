package org.gnori.elasticbot.common.phase.description.repository

import org.gnori.elasticbot.common.phase.description.entity.PhaseDescriptionValueTypeEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface PhaseDescriptionValueTypeEntityRepository : JpaRepository<PhaseDescriptionValueTypeEntity, UUID> {
    fun findByName(name: String): PhaseDescriptionValueTypeEntity?
}