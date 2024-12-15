package org.gnori.elasticbot.domain.flow.node.entity.payload

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonSubTypes.Type
import com.fasterxml.jackson.annotation.JsonTypeInfo
import org.gnori.elasticbot.domain.flow.node.entity.payload.impl.FlowNodeEntityViewPayload

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.EXISTING_PROPERTY,
    property = "type"
)
@JsonSubTypes(
    Type(
        value = FlowNodeEntityViewPayload::class,
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

        @JsonCreator
        fun from(value: String): FlowNodeEntityPayloadType? {
            return caseByValue[value]
        }
    }
}