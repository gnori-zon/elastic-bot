package org.gnori.elasticbot.telegram.file.loader

import io.github.reactivecircus.cache4k.Cache

class RefreshableCache<T : Any, R : Any>(
    private val cache: Cache<T, R>,
    private val loader: (T) -> R?
) : Cache<T, R> by cache {

    override fun get(key: T): R? {
        return cache.get(key) ?: loadAndPutIfExist(key)
    }

    private fun loadAndPutIfExist(key: T): R? {
        val loaded = loader(key)
        if (loaded != null) {
            cache.put(key, loaded)
        }
        return loaded
    }
}