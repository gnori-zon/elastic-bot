package org.gnori.bgauassistantbot.common.phase.entity

import java.util.UUID

class PhaseElementEntity(
    val id: UUID,
    val phaseId: UUID,
    val name: String,
    val description: String,
    val linkElementIds: List<UUID>,
    val displayElementIds: List<UUID>
)