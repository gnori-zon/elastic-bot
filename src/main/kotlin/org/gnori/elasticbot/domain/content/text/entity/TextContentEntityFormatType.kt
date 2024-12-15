package org.gnori.elasticbot.domain.content.text.entity

enum class TextContentEntityFormatType(val value: String) {
    HTML("html"),
    MARKDOWN("markdown"),
    ABSENT("absent");

    companion object {

        private val caseByValue =
            TextContentEntityFormatType.entries.associateBy(TextContentEntityFormatType::value)

        fun from(value: String): TextContentEntityFormatType? {
            return caseByValue[value]
        }
    }
}
