package org.gnori.bgauassistantbot.common.displayelement.repository

import org.gnori.bgauassistantbot.common.displayelement.entity.DisplayElementEntity
import org.springframework.data.r2dbc.repository.R2dbcRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface DisplayElementEntityRepository: R2dbcRepository<DisplayElementEntity, UUID>