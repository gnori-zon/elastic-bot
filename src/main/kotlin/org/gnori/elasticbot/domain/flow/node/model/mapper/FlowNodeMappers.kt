package org.gnori.elasticbot.domain.flow.node.model.mapper

import org.gnori.elasticbot.common.ext.LocalDateTime
import org.gnori.elasticbot.common.ext.orElseThrow
import org.gnori.elasticbot.domain.flow.node.entity.FlowNodeEntity
import org.gnori.elasticbot.domain.flow.node.entity.payload.impl.FlowNodeEntityViewPayload
import org.gnori.elasticbot.domain.flow.node.entity.payload.FlowNodeEntityPayload
import org.gnori.elasticbot.domain.flow.node.model.FlowNode
import org.gnori.elasticbot.domain.flow.node.model.payload.FlowNodePayload
import org.gnori.elasticbot.domain.flow.node.model.payload.FlowNodeViewPayload

fun FlowNode.Companion.from(entity: FlowNodeEntity): FlowNode {
    return FlowNode(
        id = entity.id.orElseThrow().toString(),
        parentId = entity.parentId.orElseThrow().toString(),
        payload = FlowNodePayload.from(entity.payload.orElseThrow()),
        createdAt = LocalDateTime(entity.createdAt.orElseThrow()),
        updatedAt = LocalDateTime(entity.updatedAt.orElseThrow())
    )
}

fun FlowNodePayload.Companion.from(entity: FlowNodeEntityPayload): FlowNodePayload {
    return when(entity.type) {
        org.gnori.elasticbot.domain.flow.node.entity.payload.FlowNodeEntityPayloadType.VIEW -> FlowNodeViewPayload.from(entity.casted())
    }
}

@Suppress("UNCHECKED_CAST")
fun <T: FlowNodeEntityPayload> FlowNodeEntityPayload.casted(): T {
    return this as T
}

fun FlowNodeViewPayload.Companion.from(entity: FlowNodeEntityViewPayload): FlowNodePayload {
    return FlowNodeViewPayload(
        nodeContentId = entity.nodeContentId.orElseThrow().toString()
    )
}
