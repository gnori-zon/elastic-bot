package org.gnori.bgauassistantbot.common.phase.user.subscription.service

import org.gnori.bgauassistantbot.common.phase.user.subscription.model.PhaseUserSubscription
import reactor.core.publisher.Flux

interface PhaseUserSubscriptionService {
    fun findAll(): List<PhaseUserSubscription>
}