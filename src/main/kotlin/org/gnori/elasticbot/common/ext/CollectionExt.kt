package org.gnori.elasticbot.common.ext

fun <T> Collection<T>.notContains(element: T): Boolean = !contains(element)
