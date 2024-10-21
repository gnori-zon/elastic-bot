package org.gnori.bgauassistantbot.common.phase.repository

import org.gnori.bgauassistantbot.common.phase.entity.PhaseEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.*

interface PhaseEntityRepository : JpaRepository<PhaseEntity, UUID> {

    fun findByShortId(id: Int): PhaseEntity?
    fun findByParentId(parentId: UUID): List<PhaseEntity>
    fun findByParentIdIsNull(): PhaseEntity?
    @Query("""
        SELECT p_parent.*
        FROM phases p_parent
        WHERE p_parent.id = (
            SELECT p_current.parent_id
            FROM phases p_current
            WHERE p_current.short_id = :id
        )
    """, nativeQuery = true)
    fun findByParentByShortId(id: Int): PhaseEntity?
    fun findByNameAndParentId(name: String, parentId: UUID): PhaseEntity?
    fun deleteByShortId(shortId: Int): Boolean
}