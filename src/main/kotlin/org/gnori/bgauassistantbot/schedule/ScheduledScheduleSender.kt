package org.gnori.bgauassistantbot.schedule

import dev.inmo.tgbotapi.extensions.utils.asCallbackQueryUpdate
import dev.inmo.tgbotapi.extensions.utils.extensions.raw.data
import org.gnori.bgauassistantbot.assistant.telegrambot.message.callback.CallbackDataType
import org.gnori.bgauassistantbot.assistant.telegrambot.message.preparer.model.MessageRaw
import org.gnori.bgauassistantbot.assistant.telegrambot.message.sender.model.sending.Message
import org.gnori.bgauassistantbot.common.phase.description.model.PhaseDescriptionType
import org.gnori.bgauassistantbot.common.phase.user.subscription.service.PhaseUserSubscriptionService
import org.gnori.bgauassistantbot.common.telegrambot.message.preparer.TelegramBotMessagePreparer
import org.gnori.bgauassistantbot.common.telegrambot.message.sender.TelegramBotMessageSender
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class ScheduledScheduleSender(
    private val phaseUserSubscriptionService: PhaseUserSubscriptionService,
    private val messagePreparer: TelegramBotMessagePreparer<MessageRaw, Message>,
    private val messageSender: TelegramBotMessageSender<Message>
) {

//    @Scheduled(fixedDelay = 100_000_000_000L)
    fun sendSchedules() {
        phaseUserSubscriptionService.findAll()
            .map { subscription ->
                val user = subscription.user
                val phase = subscription.phase
                val type =  PhaseDescriptionType.SCHEDULE_SEND
                val withBack = false

                MessageRaw(
                    user.chatId,
                    user, phase.shortId,
                    CallbackDataType.PHASE,
                    withBack,
                    type
                )
            }
            .map {
                messagePreparer.prepare(it)
                    .let(messageSender::send)
            }
    }
}