package org.gnori.bgauassistantbot.common.phase.user.subscription.model

import org.gnori.bgauassistantbot.common.phase.model.Phase
import org.gnori.bgauassistantbot.common.user.model.User
import org.springframework.boot.autoconfigure.security.SecurityProperties

class PhaseUserSubscriptionImpl(
    override val id: String,
    override val phase: Phase,
    override val user: User
) : PhaseUserSubscription