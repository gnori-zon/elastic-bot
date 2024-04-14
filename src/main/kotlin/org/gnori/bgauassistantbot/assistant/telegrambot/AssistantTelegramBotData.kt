package org.gnori.bgauassistantbot.assistant.telegrambot


import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.extensions.api.telegramBot
import org.gnori.bgauassistantbot.common.telegrambot.TelegramBotData
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
final class AssistantTelegramBotData(
    @Value("\${telegram.assistant.token}") private val token: String
) : TelegramBotData {
    override val telegramBot: TelegramBot = telegramBot(token)
}