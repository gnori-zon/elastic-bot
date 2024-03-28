package org.gnori.bgauassistantbot.common.linkelement.repository

import org.gnori.bgauassistantbot.common.linkelement.entity.LinkElementEntity
import org.springframework.data.r2dbc.repository.R2dbcRepository
import java.util.UUID

interface LinkElementEntityRepository : R2dbcRepository<LinkElementEntity, UUID>