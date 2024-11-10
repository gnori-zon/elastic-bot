package org.gnori.elasticbot.common.ext

import mu.KLogger
import mu.KLogging

fun <T: Any> T.logger(): Lazy<KLogger> {
    return lazy { KLogging().logger }
}