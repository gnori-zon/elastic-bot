package org.gnori.elasticbot.helpers.file.load

import arrow.core.Either
import io.github.reactivecircus.cache4k.Cache
import org.gnori.elasticbot.helpers.cache.LoadableCache

class CachedFileLoader(cacheSize: Long) : BaseFileLoader() {

    private val cache = LoadableCache(
        cache = Cache.Builder<String, ByteArray>().maximumCacheSize(cacheSize).build(),
        loader = { url -> super.load(url) }
    )

    override fun load(url: String): Either<Exception, ByteArray> {
        return cache.get(url)
    }
}