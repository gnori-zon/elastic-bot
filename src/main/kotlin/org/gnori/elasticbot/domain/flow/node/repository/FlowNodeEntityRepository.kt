package org.gnori.elasticbot.domain.flow.node.repository

import org.gnori.elasticbot.domain.flow.node.entity.FlowNodeEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface FlowNodeEntityRepository: JpaRepository<FlowNodeEntity, UUID> {

    fun findByParentId(parentId: UUID): List<FlowNodeEntity>
    fun findByParentIdIsNull(): FlowNodeEntity?
}