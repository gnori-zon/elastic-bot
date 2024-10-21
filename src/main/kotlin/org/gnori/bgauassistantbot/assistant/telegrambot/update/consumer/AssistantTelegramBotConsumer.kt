package org.gnori.bgauassistantbot.assistant.telegrambot.update.consumer

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.extensions.api.bot.setMyCommands
import dev.inmo.tgbotapi.extensions.api.chat.modify.setDefaultChatMenuButton
import dev.inmo.tgbotapi.extensions.utils.updates.retrieving.startGettingOfUpdatesByLongPolling
import dev.inmo.tgbotapi.types.BotCommand
import dev.inmo.tgbotapi.types.MenuButton
import dev.inmo.tgbotapi.types.update.abstracts.Update
import kotlinx.coroutines.runBlocking
import mu.KLogging
import org.gnori.bgauassistantbot.assistant.telegrambot.AssistantTelegramBotData
import org.gnori.bgauassistantbot.common.telegrambot.update.consumer.TelegramBotConsumer
import org.gnori.bgauassistantbot.common.telegrambot.update.processor.TelegramBotUpdateProcessor
import org.springframework.stereotype.Component

@Component
class AssistantTelegramBotConsumer(
    botData: AssistantTelegramBotData,
    private val updateProcessor: TelegramBotUpdateProcessor
) : TelegramBotConsumer {

    companion object : KLogging()

    private val telegramBot: TelegramBot = botData.telegramBot

    override val name: String = "assistant"

    override fun startConsuming() {
        telegramBot.startGettingOfUpdatesByLongPolling(
            updatesReceiver = this::consume,
            timeoutSeconds = 20,
            autoSkipTimeoutExceptions = true
        )
        runBlocking { // todo add try catch
            telegramBot.setMyCommands(BotCommand("menu", "Меню"))
            telegramBot.setDefaultChatMenuButton(MenuButton.Commands)
//            telegramBot.setDefaultChatMenuButton(MenuButton.Default)
        }
    }

    private fun consume(update: Update) {
        logger.info("consume update: ${update.updateId}")
        try {
            updateProcessor.process(update)
        } catch (exception: Exception) {
            logger.info("bad process after consuming updateId: ${update.updateId} ${exception.localizedMessage} \n ${exception.stackTrace}")
        }
    }
}