package org.gnori.elasticbot.updating.service

import org.gnori.elasticbot.common.phase.service.FlowNodeService
import org.gnori.elasticbot.updating.model.Updating
import org.gnori.elasticbot.updating.model.UpdatingImpl
import org.gnori.elasticbot.updating.model.UpdatingType
import org.gnori.elasticbot.updating.repository.UpdatingEntityRepository
import org.springframework.stereotype.Component

@Component
class UpdatingServiceImpl(
    private val repository: UpdatingEntityRepository,
    private val phaseService: FlowNodeService
) : UpdatingService {

    override fun findByType(type: UpdatingType): List<Updating> =
        repository.findByType(type.name)
            .mapNotNull { updatingEntity ->
                phaseService.findById("${updatingEntity.parentPhaseId}")
                    ?.let { UpdatingImpl("${updatingEntity.id}", type, it) }
            }
}