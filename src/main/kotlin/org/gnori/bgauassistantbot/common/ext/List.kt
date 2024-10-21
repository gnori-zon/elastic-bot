package org.gnori.bgauassistantbot.common.ext

fun <T> Collection<T>.plusIfPresent(element: T?): List<T> {
    val result = ArrayList<T>(size + 1)
    result.addAll(this)
    element?.also { result.add(it) }
    return result
}