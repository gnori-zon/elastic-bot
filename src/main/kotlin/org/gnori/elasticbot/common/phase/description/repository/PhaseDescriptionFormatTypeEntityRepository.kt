package org.gnori.elasticbot.common.phase.description.repository

import org.gnori.elasticbot.common.phase.description.entity.PhaseDescriptionFormatTypeEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface PhaseDescriptionFormatTypeEntityRepository : JpaRepository<PhaseDescriptionFormatTypeEntity, UUID> {
    fun findByName(name: String): PhaseDescriptionFormatTypeEntity?
}