package org.gnori.elasticbot.domain.content.text.entity.payload.impl

import org.gnori.elasticbot.domain.content.text.entity.payload.TextContentEntityPayload
import org.gnori.elasticbot.domain.content.text.entity.payload.TextContentEntityPayloadType

class StaticTextContentEntityPayload(
    val text: String
) : TextContentEntityPayload {
    override val type = TextContentEntityPayloadType.STATIC
}