package org.gnori.elasticbot.domain.chat.telegram.entity.payload

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonSubTypes.Type
import com.fasterxml.jackson.annotation.JsonTypeInfo
import org.gnori.elasticbot.domain.chat.telegram.entity.payload.impl.TelegramUserChatEntityPayload

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.EXISTING_PROPERTY,
    property = "type"
)
@JsonSubTypes(
    Type(
        value = TelegramUserChatEntityPayload::class,
        name = "view"
    )
)
interface TelegramChatEntityPayload {
    val type: TelegramChatEntityPayloadType
}

enum class  TelegramChatEntityPayloadType(
    val value: String
) {
    USER("user");

    companion object {

        private val caseByValue =
            entries.associateBy(TelegramChatEntityPayloadType::value)

        @JsonCreator
        fun from(value: String): TelegramChatEntityPayloadType? {
            return caseByValue[value]
        }
    }
}