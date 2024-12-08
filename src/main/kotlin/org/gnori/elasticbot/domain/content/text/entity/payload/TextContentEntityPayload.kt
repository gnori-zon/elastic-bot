package org.gnori.elasticbot.domain.content.text.entity.payload

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonSubTypes.Type
import com.fasterxml.jackson.annotation.JsonTypeInfo
import org.gnori.elasticbot.domain.content.text.entity.payload.impl.StaticTextContentEntityPayload

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.EXISTING_PROPERTY,
    property = "type"
)
@JsonSubTypes(
    Type(
        value = StaticTextContentEntityPayload::class,
        name = "static"
    )
)
interface TextContentEntityPayload {
    val type: TextContentEntityPayloadType
}

enum class TextContentEntityPayloadType(val value: String
) {
    STATIC("static");

    companion object {

        private val caseByValue =
            entries.associateBy(TextContentEntityPayloadType::value)

        fun from(value: String): TextContentEntityPayloadType? {
            return caseByValue[value]
        }
    }
}