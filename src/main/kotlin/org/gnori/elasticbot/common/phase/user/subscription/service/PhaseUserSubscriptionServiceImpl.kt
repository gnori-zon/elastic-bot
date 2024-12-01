package org.gnori.elasticbot.common.phase.user.subscription.service

import org.gnori.elasticbot.common.phase.service.FlowNodeService
import org.gnori.elasticbot.common.phase.user.subscription.model.PhaseUserSubscription
import org.gnori.elasticbot.common.phase.user.subscription.model.PhaseUserSubscriptionImpl
import org.gnori.elasticbot.common.phase.user.subscription.repository.PhaseUserSubscriptionEntityRepository
import org.gnori.elasticbot.common.user.service.UserService
import org.springframework.stereotype.Component

@Component
class PhaseUserSubscriptionServiceImpl(
    private val repository: PhaseUserSubscriptionEntityRepository,
    private val phaseService: FlowNodeService,
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