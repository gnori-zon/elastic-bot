package org.gnori.elasticbot.common.phase.user.subscription.model

import org.gnori.elasticbot.common.phase.model.FlowNode
import org.gnori.elasticbot.common.user.model.User

interface PhaseUserSubscription {
    val id: String
    val phase: FlowNode
    val user: User
}