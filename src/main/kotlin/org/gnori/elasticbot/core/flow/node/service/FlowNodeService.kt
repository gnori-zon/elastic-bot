package org.gnori.elasticbot.core.flow.node.service

import org.gnori.elasticbot.core.flow.node.model.FlowNode
import org.gnori.elasticbot.core.flow.node.model.mapper.from
import org.gnori.elasticbot.core.flow.node.repository.FlowNodeEntityRepository
import org.springframework.stereotype.Component

@Component
class FlowNodeService(
    private val repository: FlowNodeEntityRepository
) {
    fun getHeadFlowNode(): FlowNode? {
        val headEntity = repository.findByParentIdIsNull()
        return headEntity?.let { FlowNode.from(it) }
    }
}