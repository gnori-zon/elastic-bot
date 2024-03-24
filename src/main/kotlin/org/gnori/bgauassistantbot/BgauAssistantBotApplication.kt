package org.gnori.bgauassistantbot

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BgauAssistantBotApplication

fun main(args: Array<String>) {
    runApplication<BgauAssistantBotApplication>(*args)
}
