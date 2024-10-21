package org.gnori.bgauassistantbot.updating.service

import org.gnori.bgauassistantbot.common.phase.service.PhaseService
import org.gnori.bgauassistantbot.updating.model.Updating
import org.gnori.bgauassistantbot.updating.model.UpdatingImpl
import org.gnori.bgauassistantbot.updating.model.UpdatingType
import org.gnori.bgauassistantbot.updating.repository.UpdatingEntityRepository
import org.springframework.stereotype.Component

@Component
class UpdatingServiceImpl(
    private val repository: UpdatingEntityRepository,
    private val phaseService: PhaseService
) : UpdatingService {

    override fun findByType(type: UpdatingType): List<Updating> =
        repository.findByType(type.name)
            .mapNotNull { updatingEntity ->
                phaseService.findById("${updatingEntity.parentPhaseId}")
                    ?.let { UpdatingImpl("${updatingEntity.id}", type, it) }
            }
}