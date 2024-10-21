package org.gnori.bgauassistantbot.common.linkelement.repository

import org.gnori.bgauassistantbot.common.linkelement.entity.LinkElementTypeEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface LinkElementTypeEntityRepository : JpaRepository<LinkElementTypeEntity, UUID>