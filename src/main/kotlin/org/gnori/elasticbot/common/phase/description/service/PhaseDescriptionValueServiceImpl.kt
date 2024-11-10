package org.gnori.elasticbot.common.phase.description.service

import org.gnori.elasticbot.common.mapper.Mapper
import org.gnori.elasticbot.common.named.query.service.NamedQueryService
import org.gnori.elasticbot.common.phase.description.entity.PhaseDescriptionValueEntity
import org.gnori.elasticbot.common.phase.description.mapper.PhaseDescriptionValueRaw
import org.gnori.elasticbot.common.phase.description.model.CreatePhaseDescriptionValue
import org.gnori.elasticbot.common.phase.description.model.PhaseDescriptionValue
import org.gnori.elasticbot.common.phase.description.model.PhaseDescriptionValueType
import org.gnori.elasticbot.common.phase.description.repository.PhaseDescriptionValueEntityRepository
import org.springframework.stereotype.Component
import java.util.*

@Component
class PhaseDescriptionValueServiceImpl(
    private val repository: PhaseDescriptionValueEntityRepository,
    private val typeService: PhaseDescriptionValueTypeService,
    private val mapper: Mapper<PhaseDescriptionValueRaw, PhaseDescriptionValue>,
    private val namedQueryService: NamedQueryService
) : PhaseDescriptionValueService {

    override fun findBy(id: String, fieldsContext: Map<String, Any>): PhaseDescriptionValue? =
        repository.findById(UUID.fromString(id)).orElse(null)
            ?.let { valueEntity -> this.findComponentsAndMap(valueEntity, fieldsContext) }

    override fun createIfNotExist(createPhaseDescriptionValue: CreatePhaseDescriptionValue): String? {

        val typeId = typeService.getId(createPhaseDescriptionValue.type) ?: return null
        val descriptionValue = repository.findByTypeIdAndValue(
            UUID.fromString(typeId),
            createPhaseDescriptionValue.value
        ) ?: repository.saveAndFlush(
            PhaseDescriptionValueEntity(
                typeId = UUID.fromString(typeId),
                value = createPhaseDescriptionValue.value
            )
        )

        return descriptionValue.let { "${it.id}" }
    }

    private fun findComponentsAndMap(
        valueEntity: PhaseDescriptionValueEntity,
        fieldsContext: Map<String, Any>
    ): PhaseDescriptionValue? =
        typeService.findBy("${valueEntity.typeId}")
            ?.let { type ->
                when (type) {
                    PhaseDescriptionValueType.STATIC -> valueEntity.value
                    PhaseDescriptionValueType.DYNAMIC -> namedQueryService.executeQueryBy(
                        valueEntity.value,
                        fieldsContext
                    )
                        .let(::firstValueOrEmpty)
                }.let { valueText ->
                    mapper.map(PhaseDescriptionValueRaw(valueEntity.id!!, type, valueText))
                }
            }

    private fun firstValueOrEmpty(maps: List<Map<String, Any>>): String =
        maps.firstOrNull()?.values?.firstOrNull()?.let { "$it" } ?: ""
}