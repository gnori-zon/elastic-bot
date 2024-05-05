package org.gnori.bgauassistantbot.assistant.telegrambot.update.consumer

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.extensions.utils.updates.retrieving.startGettingOfUpdatesByLongPolling
import dev.inmo.tgbotapi.types.update.abstracts.Update
import mu.KLogging
import org.gnori.bgauassistantbot.assistant.telegrambot.AssistantTelegramBotData
import org.gnori.bgauassistantbot.common.telegrambot.update.consumer.TelegramBotConsumer
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import reactor.core.publisher.Sinks

@Component
class AssistantTelegramBotConsumer(
    botData: AssistantTelegramBotData,
) : TelegramBotConsumer {

    companion object : KLogging()

    private val telegramBot: TelegramBot = botData.telegramBot
    private val updatesSink = Sinks.many().replay().all<Update>()

    override val name: String = "assistant"
    override val updates: Flux<Update>
        get() = updatesSink.asFlux()

    override fun startConsuming() {
        telegramBot.startGettingOfUpdatesByLongPolling(updatesReceiver = this::consume)
    }

    private fun consume(update: Update) {
        updatesSink.tryEmitNext(update)
            .let { emittingResult ->
                logger.info("update [${update.updateId}] emitted with status: $emittingResult")
            }
    }
}