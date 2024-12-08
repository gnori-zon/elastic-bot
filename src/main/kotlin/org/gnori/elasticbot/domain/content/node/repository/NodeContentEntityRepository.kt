package org.gnori.elasticbot.domain.content.node.repository

import org.gnori.elasticbot.domain.content.node.entity.NodeContentEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface NodeContentEntityRepository : JpaRepository<NodeContentEntity, UUID>