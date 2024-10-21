package org.gnori.bgauassistantbot.common.ext

fun <T> Collection<T>.notContains(element: T): Boolean = !contains(element)