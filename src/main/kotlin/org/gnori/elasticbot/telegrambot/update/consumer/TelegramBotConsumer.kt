package org.gnori.elasticbot.telegrambot.update.consumer

import dev.inmo.tgbotapi.extensions.utils.asCallbackQueryUpdate
import dev.inmo.tgbotapi.extensions.utils.extensions.raw.data
import dev.inmo.tgbotapi.extensions.utils.extensions.raw.message
import dev.inmo.tgbotapi.extensions.utils.extensions.sourceChat
import dev.inmo.tgbotapi.extensions.utils.extensions.sourceUser
import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.extensions.api.bot.setMyCommands
import dev.inmo.tgbotapi.extensions.api.chat.modify.setDefaultChatMenuButton
import dev.inmo.tgbotapi.extensions.utils.asBaseSentMessageUpdate
import dev.inmo.tgbotapi.extensions.utils.extensions.raw.text
import dev.inmo.tgbotapi.extensions.utils.updates.retrieving.setWebhookInfoAndStartListenWebhooks
import dev.inmo.tgbotapi.extensions.utils.updates.retrieving.startGettingOfUpdatesByLongPolling
import dev.inmo.tgbotapi.types.BotCommand
import dev.inmo.tgbotapi.types.MenuButton
import dev.inmo.tgbotapi.types.update.CallbackQueryUpdate
import dev.inmo.tgbotapi.types.update.abstracts.Update
import kotlinx.coroutines.runBlocking
import org.gnori.elasticbot.telegrambot.TelegramBotKeeper
import org.gnori.elasticbot.common.ext.logger
import org.gnori.elasticbot.common.telegrambot.update.consumer.TelegramBotConsumer
import org.gnori.elasticbot.common.telegrambot.update.processor.TelegramBotUpdateProcessor
import org.gnori.elasticbot.telegrambot.update.processor.model.CallbackEvent
import org.gnori.elasticbot.telegrambot.update.processor.model.Event
import org.gnori.elasticbot.telegrambot.update.processor.model.TextEvent
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
        runBlocking {
            bot.setMyCommands(BotCommand("menu", "Меню"))
            bot.setDefaultChatMenuButton(MenuButton.Commands)
        }
    }

    private fun consume(update: Update) {
        logger.info("consume update: ${update.updateId}")
        updateProcessor.process(update.toEvent())
    }
}

private fun Update.toEvent(): Event {
    if (this is CallbackQueryUpdate) {
        return CallbackEvent(
            this.updateId.long,
            this.data.message.messageId.long,

        )
    }
    return TextEvent()

    this.asCallbackQueryUpdate()
    this.sourceUser()?.let {
        val chatId = it.id.chatId.long
        val username = it.username?.username ?: ""
    }
    this.asBaseSentMessageUpdate()
}