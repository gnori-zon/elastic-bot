package org.gnori.elasticbot.helpers.file.load

import arrow.core.Either

fun interface FileLoader {
    fun load(url: String): Either<Exception, ByteArray>
}