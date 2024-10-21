package org.gnori.bgauassistantbot.common.phase.user.subscription.entity
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.UUID

@Entity
@Table(name = "phases_users_subscription")
class PhaseUserSubscriptionEntity(
    @Id var id: UUID,
    var phaseId: UUID,
    var userId: UUID
) {
    constructor(): this(UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID())
}