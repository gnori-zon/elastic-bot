package org.gnori.elasticbot.updating.parser

interface Parser<T, R> {
    fun parse(request: T): R
}