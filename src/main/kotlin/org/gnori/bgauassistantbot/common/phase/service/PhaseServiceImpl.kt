package org.gnori.bgauassistantbot.common.phase.service

import org.gnori.bgauassistantbot.common.linkelement.model.LinkElement
import org.gnori.bgauassistantbot.common.linkelement.service.LinkElementService
import org.gnori.bgauassistantbot.common.phase.action.model.PhaseAction
import org.gnori.bgauassistantbot.common.phase.action.service.PhaseActionService
import org.gnori.bgauassistantbot.common.phase.description.model.PhaseDescription
import org.gnori.bgauassistantbot.common.phase.description.model.PhaseDescriptionImpl
import org.gnori.bgauassistantbot.common.phase.description.model.PhaseDescriptionType
import org.gnori.bgauassistantbot.common.phase.description.service.PhaseDescriptionService
import org.gnori.bgauassistantbot.common.phase.entity.PhaseEntity
import org.gnori.bgauassistantbot.common.phase.mapper.PhaseRaw
import org.gnori.bgauassistantbot.common.phase.mapper.PhaseRawToPhaseMapper
import org.gnori.bgauassistantbot.common.phase.model.CreatePhase
import org.gnori.bgauassistantbot.common.phase.model.Phase
import org.gnori.bgauassistantbot.common.phase.repository.PhaseEntityRepository
import org.springframework.stereotype.Component
import java.util.*

@Component
class PhaseServiceImpl(
    private val repository: PhaseEntityRepository,
    private val linkElementService: LinkElementService,
    private val descriptionService: PhaseDescriptionService,
    private val actionService: PhaseActionService,
    private val mapper: PhaseRawToPhaseMapper
) : PhaseService {

    override fun findById(id: String): Phase? =
        repository.findById(UUID.fromString(id)).orElse(null)
            ?.let(this::findComponentsAndMap)

    override fun findByShortId(id: Int, descriptionType: PhaseDescriptionType): Phase? =
        repository.findByShortId(id)
            ?.let { entity -> findComponentsAndMap(entity, descriptionType) }

    override fun findFirstPhase(descriptionType: PhaseDescriptionType): Phase? =
        repository.findByParentIdIsNull()
            ?.let { entity -> findComponentsAndMap(entity, descriptionType) }

    override fun findByParentId(id: String): List<Phase> =
        repository.findByParentId(UUID.fromString(id))
            .mapNotNull(this::findComponentsAndMap)

    override fun findParentByShortId(id: Int): Phase? =
        repository.findByParentByShortId(id)
            ?.let(this::findComponentsAndMap)

    override fun create(createPhase: CreatePhase): Phase? =
        repository.saveAndFlush(PhaseEntity(name = createPhase.name, parentId = UUID.fromString(createPhase.parentId)))
            .let { entity -> repository.findByNameAndParentId(entity.name, entity.parentId!!) }
            ?.let(this::findComponentsAndMap)

    override fun delete(shortId: Int): Boolean =
        repository.deleteByShortId(shortId)

    private fun findComponentsAndMap(
        entity: PhaseEntity,
        descriptionType: PhaseDescriptionType = PhaseDescriptionType.SEND
    ): Phase? {

        if (entity.id == null) {
            return null
        }

        val childNamesWithShortIds = this.findChildNamesAndShortIdsByPhaseId(entity.id!!)
        val description = this.findDescription(entity, descriptionType)
        val linkElements = this.findLinkElementsByPhaseId(entity.id!!)
        val actions = this.findActionsByPhaseId(entity.id!!)

        val phaseRaw = this.findLinkElementById(entity.headerLinkElementId)
            ?.let { PhaseRaw(entity, description, it, childNamesWithShortIds, linkElements, actions) }
            ?: PhaseRaw(entity, description, null, childNamesWithShortIds, linkElements, actions)

        return mapper.map(phaseRaw)
    }

    private fun findDescription(phase: PhaseEntity, descriptionType: PhaseDescriptionType): PhaseDescription =
        descriptionService.findBy("${phase.id}", descriptionType)
            ?: PhaseDescriptionImpl("", descriptionType, "Выберите...")

    private fun findActionsByPhaseId(phaseId: UUID): List<PhaseAction> =
        actionService.findByPhaseId("$phaseId")

    private fun findLinkElementById(headerLinkElementId: UUID?): LinkElement? =
        headerLinkElementId?.let { linkElementService.findById(it.toString()) }

    private fun findChildNamesAndShortIdsByPhaseId(phaseId: UUID): List<Pair<String, Int>> =
        repository.findByParentId(phaseId)
            .mapNotNull { phaseEntity ->
                if (phaseEntity.shortId == null) {
                    null
                } else {
                    Pair(phaseEntity.name, phaseEntity.shortId!!)
                }
            }

    private fun findLinkElementsByPhaseId(phaseId: UUID): List<LinkElement> =
        linkElementService.findByPhaseId(phaseId.toString())
}