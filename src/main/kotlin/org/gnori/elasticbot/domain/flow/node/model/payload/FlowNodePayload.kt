package org.gnori.elasticbot.domain.flow.node.model.payload

interface FlowNodePayload {
    val type: FlowNodePayloadType
    companion object
}

enum class  FlowNodePayloadType(
    val value: String
) {
    VIEW("view");

    companion object {

        private val caseByValue =
            entries.associateBy(FlowNodePayloadType::value)

        fun from(value: String): FlowNodePayloadType? {
            return caseByValue[value]
        }
    }
}