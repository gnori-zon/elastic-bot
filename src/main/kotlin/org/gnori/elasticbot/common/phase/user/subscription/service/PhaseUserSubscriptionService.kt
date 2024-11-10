package org.gnori.elasticbot.common.phase.user.subscription.service

import org.gnori.elasticbot.common.phase.user.subscription.model.PhaseUserSubscription

interface PhaseUserSubscriptionService {
    fun findAll(): List<PhaseUserSubscription>
}