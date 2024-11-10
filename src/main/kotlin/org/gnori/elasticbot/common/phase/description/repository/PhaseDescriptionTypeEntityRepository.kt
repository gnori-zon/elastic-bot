package org.gnori.elasticbot.common.phase.description.repository

import org.gnori.elasticbot.common.phase.description.entity.PhaseDescriptionTypeEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface PhaseDescriptionTypeEntityRepository : JpaRepository<PhaseDescriptionTypeEntity, UUID> {
    fun findByName(name: String): PhaseDescriptionTypeEntity?
}