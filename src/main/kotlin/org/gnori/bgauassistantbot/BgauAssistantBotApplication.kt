package org.gnori.bgauassistantbot

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.scheduling.annotation.EnableScheduling

@EnableScheduling
@EnableJpaRepositories
@SpringBootApplication
class BgauAssistantBotApplication

fun main(args: Array<String>) {
    runApplication<BgauAssistantBotApplication>(*args)
}
