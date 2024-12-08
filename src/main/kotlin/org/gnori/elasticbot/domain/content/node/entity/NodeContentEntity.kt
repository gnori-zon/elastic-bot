package org.gnori.elasticbot.domain.content.node.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.UUID

@Entity
@Table(name = "node_contents")
class NodeContentEntity(
    @Id
    val id: UUID? = null,
    val headerReferenceElementId: UUID? = null,
    val textContentId: UUID? = null,
    val createdAt: Long? = null,
    val updatedAt: Long? = null
)