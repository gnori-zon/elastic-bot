package org.gnori.bgauassistantbot.updating.parser

interface Parser<T, R> {
    fun parse(request: T): R
}