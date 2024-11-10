package org.gnori.elasticbot.common.telegrambot.starter

import org.gnori.elasticbot.common.telegrambot.update.consumer.TelegramBotConsumer
import org.springframework.stereotype.Component
import org.gnori.elasticbot.common.ext.logger

@Component
final class TelegramBotsStarter(
    bots: List<TelegramBotConsumer>
) {

    private val logger by logger()

    init {
        bots.forEach {
            logger.info("bot [${it.name}] started consuming")
            it.startConsuming()
        }
    }
}