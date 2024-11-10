package org.gnori.elasticbot.updating.parser.html.data

import com.fleeksoft.ksoup.select.Elements

class HtmlParseResponse(
    val data: Map<String, Elements>
) {
    companion object {
        val empty = HtmlParseResponse(emptyMap())
    }
}
