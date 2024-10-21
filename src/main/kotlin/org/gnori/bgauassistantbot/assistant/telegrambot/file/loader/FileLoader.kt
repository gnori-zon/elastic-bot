package org.gnori.bgauassistantbot.assistant.telegrambot.file.loader

interface FileLoader {
    fun loadBy(url: String): ByteArray?
}