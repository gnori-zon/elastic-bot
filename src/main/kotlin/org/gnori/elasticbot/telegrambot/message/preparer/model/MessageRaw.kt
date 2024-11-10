package org.gnori.elasticbot.telegrambot.message.preparer.model

import org.gnori.elasticbot.telegrambot.message.callback.CallbackDataType
import org.gnori.elasticbot.common.phase.description.model.PhaseDescriptionType
import org.gnori.elasticbot.common.user.model.User

class MessageRaw(
    val chatId: Long,
    val user: User,
    val callBackDataShortId: Int?,
    val callbackDataType: CallbackDataType?,
    val withBack: Boolean = true,
    val descriptionType: PhaseDescriptionType = PhaseDescriptionType.SEND
)