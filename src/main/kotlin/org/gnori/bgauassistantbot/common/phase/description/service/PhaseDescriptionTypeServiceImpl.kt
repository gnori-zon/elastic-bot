package org.gnori.bgauassistantbot.common.phase.description.service

import org.gnori.bgauassistantbot.common.phase.description.model.PhaseDescriptionType
import org.gnori.bgauassistantbot.common.phase.description.repository.PhaseDescriptionTypeEntityRepository
import org.springframework.stereotype.Component

@Component
class PhaseDescriptionTypeServiceImpl(
    private val repository: PhaseDescriptionTypeEntityRepository
) : PhaseDescriptionTypeService {

    override fun getId(type: PhaseDescriptionType) : String? =
        repository.findByName(type.name)?.let { "${it.id}" }
}