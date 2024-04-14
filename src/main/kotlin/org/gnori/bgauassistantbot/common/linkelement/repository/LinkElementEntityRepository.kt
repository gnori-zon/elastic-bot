package org.gnori.bgauassistantbot.common.linkelement.repository

import org.gnori.bgauassistantbot.common.linkelement.entity.LinkElementEntity
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.r2dbc.repository.R2dbcRepository
import reactor.core.publisher.Flux
import java.util.*

interface LinkElementEntityRepository : R2dbcRepository<LinkElementEntity, UUID> {

    @Query(
        """
        SELECT 
            le.id,
            le.name,
            le.link,
            le.type_id
        FROM phases_link_elements ple 
            JOIN link_elements le ON ple.link_element_id = le.id AND ple.phase_id = :phaseId 
    """
    )
    fun findByPhaseId(phaseId: UUID): Flux<LinkElementEntity>
}