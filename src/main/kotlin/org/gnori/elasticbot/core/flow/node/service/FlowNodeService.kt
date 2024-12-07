package org.gnori.elasticbot.core.flow.node.service

import org.gnori.elasticbot.core.flow.node.model.FlowNode
import org.gnori.elasticbot.core.flow.node.model.mapper.from
import org.gnori.elasticbot.core.flow.node.repository.FlowNodeEntityRepository
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class FlowNodeService(
    private val repository: FlowNodeEntityRepository
) {

    fun findHeadFlowNode(): FlowNode? {
        return repository.findByParentIdIsNull()
            ?.let(FlowNode::from)
    }

    fun findById(id: String): FlowNode? {
        return repository.findById(UUID.fromString(id))
            .orElse(null)
            ?.let(FlowNode::from)
    }

    fun listByParentId(id: String): List<FlowNode> {
        return repository.findByParentId(UUID.fromString(id))
            .map(FlowNode::from)
    }
}