package org.gnori.elasticbot.telegram.file.loader

interface FileLoader {
    fun loadBy(url: String): ByteArray?
}