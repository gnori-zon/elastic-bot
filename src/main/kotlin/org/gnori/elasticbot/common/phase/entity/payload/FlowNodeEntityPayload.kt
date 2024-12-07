package org.gnori.elasticbot.common.phase.entity.payload

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonSubTypes.Type
import com.fasterxml.jackson.annotation.JsonTypeInfo

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "type")
@JsonSubTypes(
    Type(value = FlowNotEntityViewPayload::class, name = "view")
)
interface FlowNodeEntityPayload {
    val type: FlowNodeEntityPayloadType
}