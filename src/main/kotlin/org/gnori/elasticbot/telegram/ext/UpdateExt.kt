package org.gnori.elasticbot.telegram.ext

import dev.inmo.tgbotapi.extensions.utils.extensions.sourceUser
import dev.inmo.tgbotapi.types.update.abstracts.Update

val Update.username : String
    get() = sourceUser()?.username?.username ?: ""