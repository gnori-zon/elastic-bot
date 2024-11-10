package org.gnori.elasticbot.common.phase.description.service

import org.gnori.elasticbot.common.phase.description.entity.PhaseDescriptionEntity
import org.gnori.elasticbot.common.phase.description.model.*
import org.gnori.elasticbot.common.phase.description.repository.PhaseDescriptionEntityRepository
import org.springframework.stereotype.Component
import java.util.*

@Component
class PhaseDescriptionServiceImpl(
    private val repository: PhaseDescriptionEntityRepository,
    private val typeService: PhaseDescriptionTypeService,
    private val formatTypeService: PhaseDescriptionFormatTypeService,
    private val valueService: PhaseDescriptionValueService
) : PhaseDescriptionService {

    override fun findBy(phaseId: String, type: PhaseDescriptionType): PhaseDescription? =
        typeService.getId(type)
            ?.let { typeId ->
                repository.findByPhaseIdAndTypeId(UUID.fromString(phaseId), UUID.fromString(typeId))
            }
            ?.let { entity ->
                val value = valueService.findBy("${entity.valueId}", mapOf("phase_id" to entity.phaseId))
                val format = formatTypeService.findById("${entity.formatTypeId}")

                if (value == null || format == null) {
                    null
                } else {
                    PhaseDescriptionImpl("${entity.id}", type, value.text, format)
                }
            }

    override fun createIfNotExist(createPhaseDescription: CreatePhaseDescription): String? {

        val typeId = typeService.getId(createPhaseDescription.type) ?: return null

        val phase = repository.findByPhaseIdAndTypeId(
            UUID.fromString(createPhaseDescription.phaseId),
            UUID.fromString(typeId)
        )
            ?: createPhase(typeId, createPhaseDescription)

        return phase?.let { "${it.id}" }
    }

    private fun createPhase(
        typeId: String,
        createPhaseDescription: CreatePhaseDescription
    ): PhaseDescriptionEntity? {

        val formatTypeId = formatTypeService.getId(createPhaseDescription.formatType)
        val valueId = valueService.createIfNotExist(
            CreatePhaseDescriptionValue(
                createPhaseDescription.value,
                createPhaseDescription.valueType
            )
        )

        if (formatTypeId == null || valueId == null) {
            return null
        }

        return repository.saveAndFlush(
            PhaseDescriptionEntity(
                phaseId = UUID.fromString(createPhaseDescription.phaseId),
                typeId = UUID.fromString(typeId),
                valueId = UUID.fromString(valueId),
                formatTypeId = UUID.fromString(formatTypeId)
            )
        )
    }
}