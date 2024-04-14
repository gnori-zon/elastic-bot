package org.gnori.bgauassistantbot.common.telegrambot.starter

import mu.KLogging
import org.gnori.bgauassistantbot.common.telegrambot.consumer.TelegramBotConsumer
import org.springframework.stereotype.Component

@Component
final class TelegramBotsStarter(
    bots: List<TelegramBotConsumer>
) {

    companion object : KLogging()

    init {
        bots.forEach {
            logger.info("bot [${it.name}] started consuming")
            it.startConsuming()
        }
    }
}