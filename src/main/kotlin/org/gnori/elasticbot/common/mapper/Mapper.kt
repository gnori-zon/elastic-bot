package org.gnori.elasticbot.common.mapper

interface Mapper<T, R> {
    fun map(param: T): R
}