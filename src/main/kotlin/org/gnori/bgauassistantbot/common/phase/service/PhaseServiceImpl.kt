package org.gnori.bgauassistantbot.common.phase.service

import org.gnori.bgauassistantbot.common.linkelement.model.LinkElement
import org.gnori.bgauassistantbot.common.linkelement.service.LinkElementService
import org.gnori.bgauassistantbot.common.phase.entity.PhaseEntity
import org.gnori.bgauassistantbot.common.phase.mapper.PhaseRaw
import org.gnori.bgauassistantbot.common.phase.mapper.PhaseRawToPhaseMapper
import org.gnori.bgauassistantbot.common.phase.model.Phase
import org.gnori.bgauassistantbot.common.phase.repository.PhaseEntityRepository
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import java.util.*

@Component
class PhaseServiceImpl(
    private val repository: PhaseEntityRepository,
    private val linkElementService: LinkElementService,
    private val mapper: PhaseRawToPhaseMapper
) : PhaseService {


    override fun findByName(name: String): Mono<Phase> =
        repository.findByName(name)
            .flatMap(this::findComponentsAndMap)

    private fun findComponentsAndMap(entity: PhaseEntity): Mono<Phase> =
        Mono.zip(
            this.findChildNamesById(entity.id),
            this.findLinkElementsById(entity.id),
            ::Pair
        )
            .map { (childNames, linkElements) ->
                PhaseRaw(entity, childNames, linkElements)
            }
            .map(mapper::map)

    private fun findChildNamesById(phaseId: UUID): Mono<List<String>> =
        repository.findByParentId(phaseId)
            .map { it.name }
            .collectList()

    private fun findLinkElementsById(phaseId: UUID): Mono<List<LinkElement>> =
        linkElementService.findByPhaseId(phaseId.toString())
            .collectList()
}