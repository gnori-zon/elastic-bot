package org.gnori.elasticbot.telegram.file.loader

import io.github.reactivecircus.cache4k.Cache
import org.gnori.elasticbot.common.ext.logger
import org.springframework.stereotype.Component
import java.net.URI

@Component
class FileLoaderImpl : FileLoader {

    private val logger by logger()
    private val cache = RefreshableCache(Cache.Builder<String, ByteArray>().maximumCacheSize(10).build(), ::innerLoad)

    override fun loadBy(url: String): ByteArray? {
       return cache.get(url)
    }

    private fun innerLoad(url: String): ByteArray? {
        return try {
            URI(url).toURL().readBytes();
        } catch (ex: Exception) {
            logger.info("bad load file by url: $url; ex: ${ex.message}, ${ex.stackTrace}")
            return null
        }
    }
}


