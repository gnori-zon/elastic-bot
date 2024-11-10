package org.gnori.elasticbot.common.named.query.entity
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.UUID

@Entity
@Table(name = "named_query_input_params")
class NamedQueryInputParamEntity(
    @Id var id: UUID? = null,
    var namedQueryId: UUID,
    var typeId: UUID,
    var name: String,
    var value: String
) {
    constructor() : this(namedQueryId = UUID.randomUUID(), typeId = UUID.randomUUID(), name = "", value = "")
}