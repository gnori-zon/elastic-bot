package org.gnori.bgauassistantbot.assistant.telegrambot.consumer

import dev.inmo.tgbotapi.extensions.utils.updates.retrieving.startGettingOfUpdatesByLongPolling
import dev.inmo.tgbotapi.types.update.abstracts.Update
import mu.KLogging
import org.gnori.bgauassistantbot.assistant.telegrambot.AssistantTelegramBotData
import org.gnori.bgauassistantbot.common.telegrambot.consumer.TelegramBotConsumer
import org.springframework.stereotype.Component

@Component
class AssistantTelegramBotConsumer(
    private val botData: AssistantTelegramBotData,
) : TelegramBotConsumer {

    companion object : KLogging()

    override val name: String = "assistant"

    override fun startConsuming() {
        botData.telegramBot.startGettingOfUpdatesByLongPolling(updatesReceiver = this::consume)
    }

    override fun consume(update: Update) {
        logger.info("$update")
    }
}