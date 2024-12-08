package org.gnori.elasticbot.domain.flow.node.model

import org.gnori.elasticbot.domain.flow.node.model.payload.FlowNodePayload
import java.time.LocalDateTime

class FlowNode(
    val id: String,
    val parentId: String,
    val payload: FlowNodePayload,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
){
    companion object
}