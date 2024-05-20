package org.gnori.bgauassistantbot.common.phase.repository

import org.gnori.bgauassistantbot.common.phase.entity.PhaseEntity
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.r2dbc.repository.R2dbcRepository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.*

interface PhaseEntityRepository : R2dbcRepository<PhaseEntity, UUID> {

    fun findByShortId(id: Int): Mono<PhaseEntity>
    fun findByParentId(parentId: UUID): Flux<PhaseEntity>
    fun findByParentIdIsNull(): Mono<PhaseEntity>
    @Query("""
        SELECT p_parent.*
        FROM phases p_parent
        WHERE p_parent.id = (
            SELECT p_current.parent_id
            FROM phases p_current
            WHERE p_current.short_id = :id
        )
    """)
    fun findByParentByShortId(id: Int): Mono<PhaseEntity>
}