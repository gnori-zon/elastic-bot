package org.gnori.elasticbot.updating.repository

import org.gnori.elasticbot.updating.entity.UpdatingEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UpdatingEntityRepository: JpaRepository<UpdatingEntity, UUID> {

    @Query("""
        SELECT * 
        FROM updating
        WHERE type_id = (
            SELECT t.id
            FROM updating_types t
            WHERE t.name = :typeName
        )
        """, nativeQuery = true)
    fun findByType(typeName: String): List<UpdatingEntity>
}