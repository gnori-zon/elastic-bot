package org.gnori.elasticbot.domain.flow.node.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.gnori.elasticbot.domain.flow.node.entity.payload.FlowNodeEntityPayload
import org.hibernate.annotations.JdbcTypeCode
import org.hibernate.type.SqlTypes
import java.util.UUID

@Entity
@Table(name = "flow_nodes")
class FlowNodeEntity(
    @Id
    val id: UUID? = null,
    val parentId: UUID? = null,
    @JdbcTypeCode(SqlTypes.JSON)
    val payload: FlowNodeEntityPayload? = null,
    val createdAt: Long? = null,
    val updatedAt: Long? = null
)