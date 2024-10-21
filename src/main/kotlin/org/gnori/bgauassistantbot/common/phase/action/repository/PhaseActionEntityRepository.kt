package org.gnori.bgauassistantbot.common.phase.action.repository

import org.gnori.bgauassistantbot.common.phase.action.entity.PhaseActionEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface PhaseActionEntityRepository : JpaRepository<PhaseActionEntity, UUID> {
    fun findByPhaseId(phaseId: UUID): List<PhaseActionEntity>
    fun findByShortId(shortId: Int): PhaseActionEntity?
    fun findByNameAndPhaseId(name: String, phaseId: UUID): PhaseActionEntity?
}