package org.gnori.bgauassistantbot.assistant.telegrambot


import mu.KLogging
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import dev.inmo.tgbotapi.extensions.api.telegramBot
import dev.inmo.tgbotapi.extensions.utils.updates.retrieving.startGettingOfUpdatesByLongPolling
import dev.inmo.tgbotapi.types.update.abstracts.Update
import org.gnori.bgauassistantbot.common.telegrambot.TelegramBot

@Component
final class AssistantTelegramBot(
    @Value("\${telegram.assistant.token}") private val token: String
): TelegramBot {

    companion object : KLogging()

    override val name: String = "assistant"

    override fun startConsuming() {
        telegramBot(token)
            .startGettingOfUpdatesByLongPolling(updatesReceiver = this::consume)
    }

    override fun consume(update: Update) {
        logger.info("$update")
    }
}