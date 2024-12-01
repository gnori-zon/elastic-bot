package org.gnori.elasticbot.telegram


import dev.inmo.tgbotapi.extensions.api.telegramBot
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
final class TelegramBotKeeper (
    @Value("\${bots.telegram.token}") private val token: String
) {
    val bot = telegramBot(token)
}