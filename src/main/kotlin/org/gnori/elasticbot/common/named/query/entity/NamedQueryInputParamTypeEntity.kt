package org.gnori.elasticbot.common.named.query.entity
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.UUID

@Entity
@Table(name = "named_query_input_param_types")
class NamedQueryInputParamTypeEntity(
    @Id var id: UUID,
    var name: String
) {
    constructor(): this(id = UUID.randomUUID(), name = "")
}