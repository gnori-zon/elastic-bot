package org.gnori.elasticbot.common.phase.entity.payload

import java.util.UUID

class ViewPayload(
    var flowNodeContentId: UUID? = null
) : FlowNodeEntityPayload {

    override val type: FlowNodeEntityPayloadType
        get() = FlowNodeEntityPayloadType.VIEW
}