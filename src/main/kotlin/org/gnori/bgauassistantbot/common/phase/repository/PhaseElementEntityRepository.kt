package org.gnori.bgauassistantbot.common.phase.repository

import org.gnori.bgauassistantbot.common.phase.entity.PhaseElementEntity
import org.springframework.data.r2dbc.repository.R2dbcRepository
import java.util.UUID

interface PhaseElementEntityRepository: R2dbcRepository<PhaseElementEntity, UUID>