package org.gnori.elasticbot.domain.flow.node.entity.payload.impl

import org.gnori.elasticbot.domain.flow.node.entity.payload.FlowNodeEntityPayload
import org.gnori.elasticbot.domain.flow.node.entity.payload.FlowNodeEntityPayloadType
import java.util.UUID

class FlowNodeEntityViewPayload(
    val nodeContentId: UUID
): FlowNodeEntityPayload {
    override val type = FlowNodeEntityPayloadType.VIEW
}