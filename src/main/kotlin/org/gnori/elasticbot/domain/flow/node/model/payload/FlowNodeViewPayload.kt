package org.gnori.elasticbot.domain.flow.node.model.payload

class FlowNodeViewPayload(val nodeContentId: String): FlowNodePayload {

    override val type: FlowNodePayloadType
        get() = FlowNodePayloadType.VIEW

    companion object
}