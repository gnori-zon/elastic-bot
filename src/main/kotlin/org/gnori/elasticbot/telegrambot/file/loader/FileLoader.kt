package org.gnori.elasticbot.telegrambot.file.loader

interface FileLoader {
    fun loadBy(url: String): ByteArray?
}