package org.gnori.elasticbot.core.flow.node.model.mapper

import org.gnori.elasticbot.common.ext.LocalDateTime
import org.gnori.elasticbot.common.ext.orElseThrow
import org.gnori.elasticbot.core.flow.node.entity.FlowNodeEntity
import org.gnori.elasticbot.core.flow.node.entity.impl.FlowNodeEntityViewPayload
import org.gnori.elasticbot.core.flow.node.entity.payload.FlowNodeEntityPayload
import org.gnori.elasticbot.core.flow.node.entity.payload.FlowNodeEntityPayloadType
import org.gnori.elasticbot.core.flow.node.model.FlowNode
import org.gnori.elasticbot.core.flow.node.model.payload.FlowNodePayload
import org.gnori.elasticbot.core.flow.node.model.payload.FlowNodeViewPayload

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
        FlowNodeEntityPayloadType.VIEW -> FlowNodeViewPayload.from(entity.casted())
    }
}

fun <T: FlowNodeEntityPayload> FlowNodeEntityPayload.casted(): T {
    return this as T
}

fun FlowNodeViewPayload.Companion.from(entity: FlowNodeEntityViewPayload): FlowNodePayload {
    return FlowNodeViewPayload(
        flowNodeContentId = entity.flowNodeContentId.orElseThrow().toString()
    )
}
