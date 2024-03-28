package org.gnori.bgauassistantbot.common.phase.entity

import java.util.*

class PhaseEntity(
    val id: UUID,
    val name: String,
    val description: String,
    val parentId: UUID?
)