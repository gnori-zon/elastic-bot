package org.gnori.elasticbot.helpers.cache

import arrow.core.Either
import arrow.core.right
import io.github.reactivecircus.cache4k.Cache

class LoadableCache<T : Any, R : Any, E : Exception>(
    private val cache: Cache<T, R>,
    private val loader: (T) -> Either<E, R>
) {

    fun get(key: T): Either<E, R> {
        return cache.get(key)?.right()
            ?: loader(key).onRight { loaded -> cache.put(key, loaded) }
    }
}