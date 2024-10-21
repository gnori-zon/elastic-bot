package org.gnori.bgauassistantbot.common.phase.user.subscription.service

import org.gnori.bgauassistantbot.common.phase.service.PhaseService
import org.gnori.bgauassistantbot.common.phase.user.subscription.model.PhaseUserSubscription
import org.gnori.bgauassistantbot.common.phase.user.subscription.model.PhaseUserSubscriptionImpl
import org.gnori.bgauassistantbot.common.phase.user.subscription.repository.PhaseUserSubscriptionEntityRepository
import org.gnori.bgauassistantbot.common.user.service.UserService
import org.springframework.stereotype.Component

@Component
class PhaseUserSubscriptionServiceImpl(
    private val repository: PhaseUserSubscriptionEntityRepository,
    private val phaseService: PhaseService,
    private val userService: UserService
) : PhaseUserSubscriptionService {

    override fun findAll(): List<PhaseUserSubscription> =
        repository.findAll()
            .mapNotNull {
                val phase = phaseService.findById("${it.phaseId}")
                val user = userService.findById("${it.userId}")

                if (phase == null || user == null) {
                    null
                } else {
                    PhaseUserSubscriptionImpl("${it.id}", phase, user)
                }
            }
}