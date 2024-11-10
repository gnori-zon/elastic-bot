package org.gnori.elasticbot.common.phase.user.subscription.model

import org.gnori.elasticbot.common.phase.model.Phase
import org.gnori.elasticbot.common.user.model.User

class PhaseUserSubscriptionImpl(
    override val id: String,
    override val phase: Phase,
    override val user: User
) : PhaseUserSubscription