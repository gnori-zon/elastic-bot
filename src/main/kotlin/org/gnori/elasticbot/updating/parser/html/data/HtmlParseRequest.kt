package org.gnori.elasticbot.updating.parser.html.data

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.client.reactive.ClientHttpRequest
import org.springframework.web.reactive.function.BodyInserter

class HtmlParseRequest(
    val uri: String,
    val method: HttpMethod,
    val cssSelectors: Set<String>,
    val headers: HttpHeaders = HttpHeaders(),
    val body: BodyInserter<*, in ClientHttpRequest>? = null,
)
