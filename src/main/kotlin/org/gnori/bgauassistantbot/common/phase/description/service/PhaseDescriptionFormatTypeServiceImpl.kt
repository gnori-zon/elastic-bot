package org.gnori.bgauassistantbot.common.phase.description.service

import org.gnori.bgauassistantbot.common.phase.description.model.PhaseDescriptionFormatType
import org.gnori.bgauassistantbot.common.phase.description.repository.PhaseDescriptionFormatTypeEntityRepository
import org.springframework.stereotype.Component
import java.util.*

@Component
class PhaseDescriptionFormatTypeServiceImpl(
    private val repository: PhaseDescriptionFormatTypeEntityRepository
) : PhaseDescriptionFormatTypeService {

    override fun findById(id: String): PhaseDescriptionFormatType? =
        repository.findById(UUID.fromString(id)).orElse(null)
            ?.let { PhaseDescriptionFormatType.valueOf(it.name) }

    override fun getId(type: PhaseDescriptionFormatType): String? =
        repository.findByName(type.name)
            ?.let { "${it.id}" }
}