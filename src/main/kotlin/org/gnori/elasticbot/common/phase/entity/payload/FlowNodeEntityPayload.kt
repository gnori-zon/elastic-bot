package org.gnori.elasticbot.common.phase.entity.payload

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonSubTypes.Type
import com.fasterxml.jackson.annotation.JsonTypeInfo

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes(
    Type(value = ViewPayload::class, name = "view")
)
interface FlowNodeEntityPayload {
    val type: FlowNodeEntityPayloadType
}