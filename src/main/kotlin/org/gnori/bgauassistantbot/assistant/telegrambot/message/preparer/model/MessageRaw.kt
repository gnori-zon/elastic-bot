package org.gnori.bgauassistantbot.assistant.telegrambot.message.preparer.model

import org.gnori.bgauassistantbot.assistant.telegrambot.message.callback.CallbackDataType
import org.gnori.bgauassistantbot.common.phase.description.model.PhaseDescription
import org.gnori.bgauassistantbot.common.phase.description.model.PhaseDescriptionType
import org.gnori.bgauassistantbot.common.user.model.User

class MessageRaw(
    val chatId: Long,
    val user: User,
    val callBackDataShortId: Int?,
    val callbackDataType: CallbackDataType?,
    val withBack: Boolean = true,
    val descriptionType: PhaseDescriptionType = PhaseDescriptionType.SEND
)