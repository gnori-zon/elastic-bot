package org.gnori.elasticbot.common.ext

fun <T> List<T>.addIfPresent(element: T?): List<T> {
    if (element == null) {
        return this
    }

    val result = ArrayList<T>(size + 1)
    result.addAll(this)
    result.add(element)
    return result
}