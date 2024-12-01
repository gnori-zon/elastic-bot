package org.gnori.elasticbot.common.phase.user.subscription.model

import org.gnori.elasticbot.common.phase.model.FlowNode
import org.gnori.elasticbot.common.user.model.User

class PhaseUserSubscriptionImpl(
    override val id: String,
    override val phase: FlowNode,
    override val user: User
) : PhaseUserSubscription