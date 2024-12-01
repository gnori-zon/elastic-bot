package org.gnori.elasticbot.common.phase.repository

import org.gnori.elasticbot.common.phase.entity.FlowNodeEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface FlowNodeEntityRepository : JpaRepository<FlowNodeEntity, UUID> {

    fun findByParentId(parentId: UUID): List<FlowNodeEntity>
    fun findByParentIdIsNull(): FlowNodeEntity?
    fun findByNameAndParentId(name: String, parentId: UUID): FlowNodeEntity?
}