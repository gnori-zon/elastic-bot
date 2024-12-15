package org.gnori.elasticbot.domain.data.url.converter

import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter
import org.gnori.elasticbot.common.ext.orElseThrow
import org.gnori.elasticbot.domain.data.url.entity.UrlDataEntityType

@Converter(autoApply = true)
class UrlDataEntityTypeConverter : AttributeConverter<UrlDataEntityType, String> {

    override fun convertToDatabaseColumn(type: UrlDataEntityType?): String {
        return type?.value.orElseThrow()
    }

    override fun convertToEntityAttribute(rawType: String?): UrlDataEntityType {
        return rawType?.let(UrlDataEntityType::from)
            .orElseThrow()
    }
}