package org.gnori.bgauassistantbot.common.phase.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.util.*

@Table(name = "phases")
class PhaseEntity(
    @Id val id: UUID,
    val name: String,
    val description: String,
    val parentId: UUID?
)