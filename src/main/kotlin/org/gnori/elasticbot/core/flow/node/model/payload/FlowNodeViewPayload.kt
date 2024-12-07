package org.gnori.elasticbot.core.flow.node.model.payload

class FlowNodeViewPayload(val flowNodeContentId: String): FlowNodePayload {

    override val type: FlowNodePayloadType
        get() = FlowNodePayloadType.VIEW

    companion object
}