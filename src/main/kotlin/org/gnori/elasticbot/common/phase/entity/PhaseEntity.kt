package org.gnori.elasticbot.common.phase.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.*

@Entity
@Table(name = "phases")
class PhaseEntity(
    @Id var id: UUID? = null,
    var name: String,
    var headerLinkElementId: UUID? = null,
    var shortId: Int? = null,
    var parentId: UUID? = null
) {
    constructor() : this(name = "")
}