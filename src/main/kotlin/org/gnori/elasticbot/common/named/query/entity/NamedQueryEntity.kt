package org.gnori.elasticbot.common.named.query.entity
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.UUID

@Entity
@Table(name = "named_queries")
class NamedQueryEntity(
    @Id var id: UUID? = null,
    var name: String,
    var query: String,
    var typeId: UUID
) {
    constructor(): this(name = "", query = "", typeId = UUID.randomUUID())
}