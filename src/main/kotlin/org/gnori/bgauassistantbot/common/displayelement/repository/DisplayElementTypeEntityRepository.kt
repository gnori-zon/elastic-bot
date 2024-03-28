package org.gnori.bgauassistantbot.common.displayelement.repository

import org.gnori.bgauassistantbot.common.displayelement.entity.DisplayElementTypeEntity
import org.springframework.data.r2dbc.repository.R2dbcRepository
import java.util.UUID

interface DisplayElementTypeEntityRepository : R2dbcRepository<DisplayElementTypeEntity, UUID>