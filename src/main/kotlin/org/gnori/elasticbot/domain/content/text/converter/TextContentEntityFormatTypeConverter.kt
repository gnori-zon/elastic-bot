package org.gnori.elasticbot.domain.content.text.converter

import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter
import org.gnori.elasticbot.common.ext.orElseThrow
import org.gnori.elasticbot.domain.content.text.entity.TextContentEntityFormatType

@Converter(autoApply = true)
class TextContentEntityFormatTypeConverter : AttributeConverter<TextContentEntityFormatType, String> {

    override fun convertToDatabaseColumn(type: TextContentEntityFormatType?): String {
        return type?.value.orElseThrow()
    }

    override fun convertToEntityAttribute(rawType: String?): TextContentEntityFormatType {
        return rawType?.let(TextContentEntityFormatType::from)
            .orElseThrow()
    }
}