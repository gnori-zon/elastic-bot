package org.gnori.bgauassistantbot.common.linkelement.entity
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.*

@Entity
@Table(name = "link_element_types")
class LinkElementTypeEntity(
    @Id var id: UUID,
    var name: String
) {
    constructor() : this(id = UUID.randomUUID(), name = "")
}