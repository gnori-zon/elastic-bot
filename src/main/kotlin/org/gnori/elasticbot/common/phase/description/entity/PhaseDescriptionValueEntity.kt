package org.gnori.elasticbot.common.phase.description.entity
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.UUID

@Entity
@Table(name = "phase_description_values")
class PhaseDescriptionValueEntity(
    @Id var id: UUID? = null,
    var typeId: UUID,
    var value: String
) {
    constructor(): this(typeId = UUID.randomUUID(), value = "")
}