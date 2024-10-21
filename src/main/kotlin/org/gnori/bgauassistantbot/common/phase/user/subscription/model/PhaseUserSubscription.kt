package org.gnori.bgauassistantbot.common.phase.user.subscription.model

import org.gnori.bgauassistantbot.common.phase.model.Phase
import org.gnori.bgauassistantbot.common.user.model.User

interface PhaseUserSubscription {
    val id: String
    val phase: Phase
    val user: User
}