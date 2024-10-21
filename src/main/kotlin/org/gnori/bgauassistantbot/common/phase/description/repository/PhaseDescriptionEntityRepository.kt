package org.gnori.bgauassistantbot.common.phase.description.repository

import org.gnori.bgauassistantbot.common.phase.description.entity.PhaseDescriptionEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface PhaseDescriptionEntityRepository : JpaRepository<PhaseDescriptionEntity, UUID> {
    fun findByPhaseIdAndTypeId(phaseId: UUID, typeId: UUID): PhaseDescriptionEntity?
}