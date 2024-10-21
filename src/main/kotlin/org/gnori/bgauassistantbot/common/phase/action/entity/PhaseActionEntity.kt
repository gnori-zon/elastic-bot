package org.gnori.bgauassistantbot.common.phase.action.entity

import jakarta.persistence.Table
import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.util.UUID

@Entity
@Table(name = "phase_actions")
class PhaseActionEntity(
    @Id var id: UUID? = null,
    var shortId: Int? = null,
    var phaseId: UUID,
    var name: String,
    var redirectPhaseId: UUID? = null,
    var displayConditionNamedQueryId: UUID? = null,
    var actionNamedQueryId: UUID
) {
    constructor(): this(phaseId = UUID.randomUUID(), name = "", actionNamedQueryId = UUID.randomUUID())
}