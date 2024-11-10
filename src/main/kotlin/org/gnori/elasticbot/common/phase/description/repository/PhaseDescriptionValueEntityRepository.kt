package org.gnori.elasticbot.common.phase.description.repository

import org.gnori.elasticbot.common.phase.description.entity.PhaseDescriptionValueEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface PhaseDescriptionValueEntityRepository : JpaRepository<PhaseDescriptionValueEntity, UUID> {
    fun findByTypeIdAndValue(typeId: UUID, value: String): PhaseDescriptionValueEntity?
}