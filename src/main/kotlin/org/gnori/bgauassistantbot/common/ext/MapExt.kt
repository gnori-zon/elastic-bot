package org.gnori.bgauassistantbot.common.ext

inline fun <K, V> Iterable<K>.toMap(crossinline valueGenerator: (K) -> V): Map<K, V> =
    this.toMap({ item -> item }, valueGenerator)

inline fun <T, K, V> Iterable<T>.toMap(keyGenerator: (T) -> K, valueGenerator: (T) -> V): Map<K, V> {

    val destination = LinkedHashMap<K, V>()
    for (item in this) {
        val value = valueGenerator(item)
        val key = keyGenerator(item)
        destination[key] = value
    }
    return destination
}