package org.gnori.bgauassistantbot.assistant.telegrambot.file.loader

import io.github.reactivecircus.cache4k.Cache
import org.springframework.stereotype.Component
import java.net.URI

@Component
class FileLoaderImpl : FileLoader {

    private val cache = Cache.Builder<String, ByteArray>().maximumCacheSize(10).build();

    override fun loadBy(url: String): ByteArray? {
        try {
            val optionalBytesFromCache = cache.get(url)
            if (optionalBytesFromCache != null) {
                return optionalBytesFromCache
            }

            val remoteFileBytes = URI(url).toURL().readBytes()
            cache.put(url, remoteFileBytes)
            return remoteFileBytes;
        } catch (ex: Exception) {
            return null;
        }
    }

}
