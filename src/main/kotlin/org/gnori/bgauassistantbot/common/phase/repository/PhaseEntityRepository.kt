package org.gnori.bgauassistantbot.common.phase.repository

import org.gnori.bgauassistantbot.common.phase.entity.PhaseEntity
import org.springframework.data.r2dbc.repository.R2dbcRepository
import java.util.UUID

interface PhaseEntityRepository : R2dbcRepository<PhaseEntity, UUID>