package org.gnori.bgauassistantbot.common.mapper

interface Mapper<T, R> {
    fun map(param: T): R
}