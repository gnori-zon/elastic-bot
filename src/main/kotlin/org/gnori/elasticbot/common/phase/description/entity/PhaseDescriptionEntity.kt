package org.gnori.elasticbot.common.phase.description.entity
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.UUID

@Entity
@Table(name = "phase_descriptions")
class PhaseDescriptionEntity(
    @Id var id: UUID? = null,
    var phaseId: UUID,
    var formatTypeId: UUID,
    var typeId: UUID,
    var valueId: UUID
) {
    constructor(): this(phaseId = UUID.randomUUID(), formatTypeId = UUID.randomUUID(), typeId = UUID.randomUUID(), valueId = UUID.randomUUID())
}