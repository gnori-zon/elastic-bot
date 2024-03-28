package org.gnori.bgauassistantbot.common.linkelement.repository

import org.gnori.bgauassistantbot.common.linkelement.entity.LinkElementTypeEntity
import org.springframework.data.r2dbc.repository.R2dbcRepository
import java.util.UUID

interface LinkElementTypeEntityRepository : R2dbcRepository<LinkElementTypeEntity, UUID>