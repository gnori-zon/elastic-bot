package org.gnori.bgauassistantbot.common.phase.user.subscription.repository

import org.gnori.bgauassistantbot.common.phase.user.subscription.entity.PhaseUserSubscriptionEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface PhaseUserSubscriptionEntityRepository : JpaRepository<PhaseUserSubscriptionEntity, UUID> {
}