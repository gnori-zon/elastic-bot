package org.gnori.elasticbot.domain.data.url.entity

enum class UrlDataEntityType(
    val value: String
) {
    DOCUMENT("document"),
    PHOTO("photo"),
    VIDEO("video"),
    URL("url");

    companion object {

        private val caseByValue =
            UrlDataEntityType.entries.associateBy(UrlDataEntityType::value)

        fun from(value: String): UrlDataEntityType? {
            return caseByValue[value]
        }
    }
}