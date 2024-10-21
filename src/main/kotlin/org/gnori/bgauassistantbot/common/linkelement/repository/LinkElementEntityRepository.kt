package org.gnori.bgauassistantbot.common.linkelement.repository

import org.gnori.bgauassistantbot.common.linkelement.entity.LinkElementEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.*

interface LinkElementEntityRepository : JpaRepository<LinkElementEntity, UUID> {

    @Query(
        """
        SELECT 
            le.id,
            le.name,
            le.link,
            le.type_id
        FROM phases_link_elements ple 
            JOIN link_elements le ON ple.link_element_id = le.id AND ple.phase_id = :phaseId 
    """, nativeQuery = true)
    fun findByPhaseId(phaseId: UUID): List<LinkElementEntity>
}