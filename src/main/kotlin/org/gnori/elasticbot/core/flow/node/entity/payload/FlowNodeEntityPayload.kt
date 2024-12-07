package org.gnori.elasticbot.core.flow.node.entity.payload

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonSubTypes.Type
import com.fasterxml.jackson.annotation.JsonTypeInfo
import org.gnori.elasticbot.common.phase.entity.payload.FlowNotEntityViewPayload

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "type"
)
@JsonSubTypes(
    Type(
        value = FlowNotEntityViewPayload::class,
        name = "view"
    )
)
interface FlowNodeEntityPayload {
    val type: FlowNodeEntityPayloadType
}

enum class  FlowNodeEntityPayloadType(
    val value: String
) {
    VIEW("view");
    
    companion object {

        private val caseByValue =
            entries.associateBy(FlowNodeEntityPayloadType::value)

        fun from(value: String): FlowNodeEntityPayloadType? {
            return caseByValue[value]
        }
    }
}