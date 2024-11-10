package org.gnori.elasticbot.updating.parser.html

import com.fasterxml.jackson.databind.ObjectMapper
import com.fleeksoft.ksoup.Ksoup
import org.gnori.elasticbot.updating.parser.Parser
import org.gnori.elasticbot.updating.parser.html.data.HtmlParseRequest
import org.gnori.elasticbot.updating.parser.html.data.HtmlParseResponse
import org.springframework.http.HttpHeaders.CONTENT_TYPE
import org.springframework.http.MediaType.TEXT_HTML
import org.springframework.http.MediaType.TEXT_HTML_VALUE
import org.springframework.http.codec.ClientCodecConfigurer
import org.springframework.http.codec.json.Jackson2JsonDecoder
import org.springframework.http.codec.json.Jackson2JsonEncoder
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.ExchangeStrategies
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono


@Component
class HtmlParser : Parser<HtmlParseRequest, HtmlParseResponse> {

    private val bufferedSizeBytes: Int = 128_000_000

    override fun parse(request: HtmlParseRequest): HtmlParseResponse {

        return buildHttpRequest(request)
            .retrieve()
            .bodyToMono<String>()
            .map { html ->
                Ksoup.parse(html)
                    .let { document -> request.cssSelectors.associateBy({ it }, document::select) }
                    .let(::HtmlParseResponse)
            }.block() ?: HtmlParseResponse.empty
    }

    private fun buildHttpRequest(request: HtmlParseRequest): WebClient.RequestHeadersSpec<*> {

        val requestSpec = WebClient.builder()
            .baseUrl(request.uri)
            .exchangeStrategies(
                ExchangeStrategies.builder()
                    .codecs(this::acceptedCodecs)
                    .build()
            )
            .defaultRequest { requestSpec ->
                requestSpec.headers {
                    it.add(CONTENT_TYPE, TEXT_HTML_VALUE)
                    it.addAll(request.headers)
                }
            }
            .build()
            .method(request.method)

        return request.body?.let { requestSpec.body(it) } ?: requestSpec
    }

    private fun acceptedCodecs(clientCodecConfigurer: ClientCodecConfigurer) {
        clientCodecConfigurer.defaultCodecs().maxInMemorySize(bufferedSizeBytes)
        clientCodecConfigurer.customCodecs().registerWithDefaultConfig(Jackson2JsonDecoder(ObjectMapper(), TEXT_HTML))
        clientCodecConfigurer.customCodecs().registerWithDefaultConfig(Jackson2JsonEncoder(ObjectMapper(), TEXT_HTML))
    }
}


