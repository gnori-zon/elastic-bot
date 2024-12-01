package org.gnori.elasticbot.telegram.update.consumer

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.extensions.utils.updates.retrieving.startGettingOfUpdatesByLongPolling
import dev.inmo.tgbotapi.types.update.abstracts.Update
import org.gnori.elasticbot.common.ext.logger
import org.gnori.elasticbot.common.telegrambot.update.consumer.TelegramBotConsumer
import org.gnori.elasticbot.common.telegrambot.update.processor.TelegramBotUpdateProcessor
import org.gnori.elasticbot.telegram.TelegramBotKeeper
import org.gnori.elasticbot.telegram.mapper.toEvent
import org.springframework.stereotype.Component

@Component
class TelegramBotConsumer(
    botKeeper: TelegramBotKeeper,
    private val updateProcessor: TelegramBotUpdateProcessor
) : TelegramBotConsumer {

    private val logger by logger()
    private val bot: TelegramBot = botKeeper.bot

    override val name: String = "tg-bot-1"

    override fun startConsuming() {

        bot.startGettingOfUpdatesByLongPolling(
            updatesReceiver = this::consume,
            timeoutSeconds = 20,
            autoSkipTimeoutExceptions = true
        )
    }

    private fun consume(update: Update) {
        logger.info("consume update: ${update.updateId}")
        val event = update.toEvent()
        if (event == null) {
            logger.warn("bad convert update: ${update.updateId}; class: ${update.javaClass}")
            return
        }
        updateProcessor.process(event)
    }
}
