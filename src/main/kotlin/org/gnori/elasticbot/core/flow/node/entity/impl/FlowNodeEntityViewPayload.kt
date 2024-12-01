package org.gnori.elasticbot.core.flow.node.entity.impl

import org.gnori.elasticbot.core.flow.node.entity.payload.FlowNodeEntityPayload
import org.gnori.elasticbot.core.flow.node.entity.payload.FlowNodeEntityPayloadType
import java.util.UUID

class FlowNodeEntityViewPayload(
    val flowNodeContentId: UUID
): FlowNodeEntityPayload {
    override val type = FlowNodeEntityPayloadType.VIEW
}