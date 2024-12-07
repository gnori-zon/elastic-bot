package org.gnori.elasticbot.common.phase.entity.payload

import java.util.UUID

class FlowNotEntityViewPayload(
    var flowNodeContentId: UUID? = null
) : FlowNodeEntityPayload {

    override val type: FlowNodeEntityPayloadType
        get() = FlowNodeEntityPayloadType.VIEW
}