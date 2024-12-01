package org.gnori.elasticbot.common.phase.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.gnori.elasticbot.common.phase.entity.payload.FlowNodeEntityPayload
import org.hibernate.annotations.JdbcTypeCode
import org.hibernate.type.SqlTypes
import java.util.*

@Entity
@Table(name = "flow_nodes")
class FlowNodeEntity(
    @Id
    var id: UUID? = null,
    var name: String? = null,
    @JdbcTypeCode(SqlTypes.JSON)
    var payload: FlowNodeEntityPayload? = null,
    var parentId: UUID? = null
)