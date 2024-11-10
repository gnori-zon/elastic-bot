package org.gnori.elasticbot.common.phase.description.service

import org.gnori.elasticbot.common.mapper.Mapper
import org.gnori.elasticbot.common.phase.description.entity.PhaseDescriptionValueTypeEntity
import org.gnori.elasticbot.common.phase.description.model.PhaseDescriptionValueType
import org.gnori.elasticbot.common.phase.description.repository.PhaseDescriptionValueTypeEntityRepository
import org.springframework.stereotype.Component
import java.util.*

@Component
class PhaseDescriptionValueTypeServiceImpl(
    private val repository: PhaseDescriptionValueTypeEntityRepository,
    private val mapper: Mapper<PhaseDescriptionValueTypeEntity, PhaseDescriptionValueType>
) : PhaseDescriptionValueTypeService {
    override fun findBy(id: String): PhaseDescriptionValueType? =
        repository.findById(UUID.fromString(id)).orElse(null)
            ?.let(mapper::map)

    override fun getId(valueType: PhaseDescriptionValueType): String? =
        repository.findByName(valueType.name)
            ?.let { "${it.id}" }
}