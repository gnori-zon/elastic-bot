package org.gnori.bgauassistantbot.common.phase.description.repository

import org.gnori.bgauassistantbot.common.phase.description.entity.PhaseDescriptionFormatTypeEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface PhaseDescriptionFormatTypeEntityRepository : JpaRepository<PhaseDescriptionFormatTypeEntity, UUID> {
    fun findByName(name: String): PhaseDescriptionFormatTypeEntity?
}