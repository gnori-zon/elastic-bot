package org.gnori.elasticbot.helpers.file.load

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import org.gnori.elasticbot.common.ext.logger
import java.net.URI

open class BaseFileLoader : FileLoader {

    private val logger by logger()

    override fun load(url: String): Either<Exception, ByteArray> {
        return try {
            URI(url).toURL().readBytes().right()
        } catch (exception: Exception) {
            logger.error("bad load file by url: $url", exception)
            exception.left()
        }
    }
}