package org.gnori.bgauassistantbot.common.telegrambot.starter

import mu.KLogging
import org.gnori.bgauassistantbot.common.telegrambot.TelegramBot
import org.springframework.stereotype.Component

@Component
final class TelegramBotsStarter(
    bots: List<TelegramBot>
) {

    companion object : KLogging()

    init {
        bots.forEach {
            logger.info("bot [${it.name}] started consuming")
            it.startConsuming()
        }
    }
}