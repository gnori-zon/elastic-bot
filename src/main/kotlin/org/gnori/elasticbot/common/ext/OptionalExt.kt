package org.gnori.elasticbot.common.ext

fun <T : Any> T?.orElseThrow(exception: Exception = NullPointerException()): T {
    return this ?: throw exception
}