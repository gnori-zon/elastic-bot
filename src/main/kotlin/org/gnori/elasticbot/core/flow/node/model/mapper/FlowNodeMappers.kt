package org.gnori.elasticbot.core.flow.node.model.mapper

import org.gnori.elasticbot.core.flow.node.entity.FlowNodeEntity
import org.gnori.elasticbot.core.flow.node.model.FlowNode

fun FlowNode.Companion.from(value: FlowNodeEntity): FlowNode {
    return FlowNode()
}