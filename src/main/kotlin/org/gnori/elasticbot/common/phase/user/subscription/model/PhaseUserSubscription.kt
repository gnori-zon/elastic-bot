package org.gnori.elasticbot.common.phase.user.subscription.model

import org.gnori.elasticbot.common.phase.model.Phase
import org.gnori.elasticbot.common.user.model.User

interface PhaseUserSubscription {
    val id: String
    val phase: Phase
    val user: User
}