package org.gnori.elasticbot.common.phase.service

import org.gnori.elasticbot.common.linkelement.model.LinkElement
import org.gnori.elasticbot.common.linkelement.service.LinkElementService
import org.gnori.elasticbot.common.phase.action.model.PhaseAction
import org.gnori.elasticbot.common.phase.action.service.PhaseActionService
import org.gnori.elasticbot.common.phase.description.model.PhaseDescription
import org.gnori.elasticbot.common.phase.description.model.PhaseDescriptionImpl
import org.gnori.elasticbot.common.phase.description.model.PhaseDescriptionType
import org.gnori.elasticbot.common.phase.description.service.PhaseDescriptionService
import org.gnori.elasticbot.common.phase.entity.FlowNodeEntity
import org.gnori.elasticbot.common.phase.mapper.PhaseRaw
import org.gnori.elasticbot.common.phase.mapper.PhaseRawToPhaseMapper
import org.gnori.elasticbot.common.phase.model.CreatePhase
import org.gnori.elasticbot.common.phase.model.FlowNode
import org.gnori.elasticbot.common.phase.repository.FlowNodeEntityRepository
import org.springframework.stereotype.Component
import java.util.*

@Component
class FlowNodeServiceImpl(
    private val repository: FlowNodeEntityRepository,
    private val linkElementService: LinkElementService,
    private val descriptionService: PhaseDescriptionService,
    private val actionService: PhaseActionService,
    private val mapper: PhaseRawToPhaseMapper
) : FlowNodeService {

    override fun findById(id: String): FlowNode? =
        repository.findById(UUID.fromString(id)).orElse(null)
            ?.let(this::findComponentsAndMap)

    override fun findStart(): FlowNode? {
        return repository.findByParentIdIsNull()
            ?.let(this::findComponentsAndMap)
    }

    override fun create(createPhase: CreatePhase): FlowNode? =
        repository.saveAndFlush(FlowNodeEntity(name = createPhase.name, parentId = UUID.fromString(createPhase.parentId)))
            .let { entity -> repository.findByNameAndParentId(entity.name, entity.parentId!!) }
            ?.let(this::findComponentsAndMap)

    override fun delete(id: String) =
        repository.deleteById(UUID.fromString(id))

    private fun findComponentsAndMap(
        entity: FlowNodeEntity,
        descriptionType: PhaseDescriptionType = PhaseDescriptionType.SEND
    ): FlowNode? {

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

    private fun findDescription(phase: FlowNodeEntity, descriptionType: PhaseDescriptionType): PhaseDescription =
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