package org.gnori.elasticbot.updating.entity
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.UUID

@Entity
@Table(name = "updating_types")
class UpdatingTypeEntity(
    @Id var id: UUID,
    var name: String
) {
    constructor(): this(UUID.randomUUID(), "")
}