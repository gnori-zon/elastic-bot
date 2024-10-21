package org.gnori.bgauassistantbot.common.phase.description.entity
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.UUID

@Entity
@Table(name = "phase_description_value_types")
class PhaseDescriptionValueTypeEntity(
    @Id var id: UUID,
    var name: String
) {
    constructor(): this(UUID.randomUUID(), "")
}