package org.gnori.elasticbot.common.phase.description.entity
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.UUID

@Entity
@Table(name = "phase_description_types")
class PhaseDescriptionTypeEntity(
    @Id var id: UUID,
    var name: String
) {
    constructor(): this(UUID.randomUUID(), "")
}