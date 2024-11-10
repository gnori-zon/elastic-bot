package org.gnori.elasticbot.common.linkelement.entity
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.*

@Entity
@Table(name = "link_elements")
class LinkElementEntity(
    @Id var id: UUID,
    var name: String,
    var link: String,
    var typeId: UUID
) {
    constructor() : this(id = UUID.randomUUID(), name = "", link = "", typeId =UUID.randomUUID())
}