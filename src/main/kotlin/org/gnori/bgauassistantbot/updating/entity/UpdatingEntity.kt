package org.gnori.bgauassistantbot.updating.entity

import jakarta.persistence.Table
import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.util.UUID

@Entity
@Table(name = "updating")
class UpdatingEntity(
    @Id var id: UUID,
    var typeId: UUID,
    var parentPhaseId: UUID
) {
    constructor(): this(UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID())
}