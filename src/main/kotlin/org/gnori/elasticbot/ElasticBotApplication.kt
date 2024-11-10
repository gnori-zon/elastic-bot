package org.gnori.elasticbot

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.scheduling.annotation.EnableScheduling

@EnableScheduling
@EnableJpaRepositories
@SpringBootApplication
class ElasticBotApplication

fun main(args: Array<String>) {
    runApplication<ElasticBotApplication>(*args)
}
