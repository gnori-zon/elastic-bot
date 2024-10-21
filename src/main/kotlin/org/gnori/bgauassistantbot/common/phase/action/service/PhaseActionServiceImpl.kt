package org.gnori.bgauassistantbot.common.phase.action.service

import org.gnori.bgauassistantbot.common.named.query.service.NamedQueryService
import org.gnori.bgauassistantbot.common.phase.action.entity.PhaseActionEntity
import org.gnori.bgauassistantbot.common.phase.action.model.CreatePhaseAction
import org.gnori.bgauassistantbot.common.phase.action.model.PhaseAction
import org.gnori.bgauassistantbot.common.phase.action.model.PhaseActionImpl
import org.gnori.bgauassistantbot.common.phase.action.repository.PhaseActionEntityRepository
import org.gnori.bgauassistantbot.common.phase.repository.PhaseEntityRepository
import org.springframework.stereotype.Component
import java.util.*

@Component
class PhaseActionServiceImpl(
    private val repository: PhaseActionEntityRepository,
    private val phaseRepository: PhaseEntityRepository,
    private val namedQueryService: NamedQueryService
) : PhaseActionService {
    override fun findById(id: String): PhaseAction? =
        repository.findById(UUID.fromString(id)).orElse(null)
            ?.let(::findComponentsAndMap)

    override fun findByShortId(shortId: Int): PhaseAction? =
        repository.findByShortId(shortId)
            ?.let(::findComponentsAndMap)

    override fun findByPhaseId(phaseId: String): List<PhaseAction> =
        repository.findByPhaseId(UUID.fromString(phaseId))
            .mapNotNull(::findComponentsAndMap)

    override fun create(createPhaseAction: CreatePhaseAction): PhaseAction? =
        repository.save(
            PhaseActionEntity(
                phaseId = UUID.fromString(createPhaseAction.phaseId),
                name = createPhaseAction.name,
                redirectPhaseId = createPhaseAction.redirectPhaseId?.let(UUID::fromString),
                displayConditionNamedQueryId = UUID.fromString(createPhaseAction.displayConditionNamedQueryId),
                actionNamedQueryId = UUID.fromString(createPhaseAction.actionNamedQueryId)
            )
        )
            .let(this::findComponentsAndMap)

    private fun findComponentsAndMap(entity: PhaseActionEntity): PhaseAction? {

        val actionNamedQuery = namedQueryService.findBy("${entity.actionNamedQueryId}")
        val redirectPhaseShortId = phaseRepository.findById(entity.redirectPhaseId ?: entity.phaseId)
            .map { it.shortId }
            .orElse(null)

        if (redirectPhaseShortId == null || actionNamedQuery == null || entity.shortId == null) {
            return null
        }

        val displayConditionNamedQuery = entity.displayConditionNamedQueryId
            ?.let { namedQueryService.findBy("$it") }

        return PhaseActionImpl(
            "${entity.id}",
            entity.shortId!!,
            entity.name,
            "${entity.phaseId}",
            redirectPhaseShortId,
            displayConditionNamedQuery,
            actionNamedQuery
        )
    }
}