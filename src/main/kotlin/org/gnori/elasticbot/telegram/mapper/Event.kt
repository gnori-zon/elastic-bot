package org.gnori.elasticbot.telegram.mapper

import org.gnori.elasticbot.common.user.model.User
import org.gnori.elasticbot.telegram.authorizer.user.model.UserAuthorizationParams
import org.gnori.elasticbot.telegram.ext.callbackData
import org.gnori.elasticbot.telegram.message.deleter.model.DeleteMessageParams
import org.gnori.elasticbot.telegram.message.preparer.model.MessageRaw
import org.gnori.elasticbot.telegram.update.processor.model.Event

fun Event.toDeleteMessageParams(): DeleteMessageParams {
    return DeleteMessageParams(chatId = chatId, messageId = messageId)
}

fun Event.toUserAuthorizationParams(): UserAuthorizationParams {
    return UserAuthorizationParams(username = username, chatId = chatId)
}

fun Event.toMessageRaw(user: User): MessageRaw {
    return MessageRaw(chatId, user, callbackData)
}