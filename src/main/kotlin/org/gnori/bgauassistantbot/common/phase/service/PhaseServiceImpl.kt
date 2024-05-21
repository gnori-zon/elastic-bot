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


    override fun findByShortId(id: Int): Mono<Phase> =
        repository.findByShortId(id)
            .flatMap(this::findComponentsAndMap)

    override fun findFirstPhase(): Mono<Phase> =
        repository.findByParentIdIsNull()
            .flatMap(this::findComponentsAndMap)

    override fun findParentByShortId(id: Int): Mono<Phase> =
        repository.findByParentByShortId(id)
            .flatMap(this::findComponentsAndMap)

    private fun findComponentsAndMap(entity: PhaseEntity): Mono<Phase> =
        Mono.zip(
            this.findChildNamesAndShortIdsByPhaseId(entity.id),
            this.findLinkElementsByPhaseId(entity.id),
            ::Pair
        )
            .flatMap { (childNamesWithShortIds, linkElements) ->
                findLinkElementById(entity.headerLinkElementId)
                    .map { PhaseRaw(entity, it, childNamesWithShortIds, linkElements) }
                    .switchIfEmpty(Mono.defer {
                        Mono.just(PhaseRaw(entity, null, childNamesWithShortIds, linkElements))
                    })
            }
            .map(mapper::map)

    private fun findLinkElementById(headerLinkElementId: UUID?): Mono<LinkElement> {
        return headerLinkElementId?.let { linkElementService.findById(it.toString()) }
            ?: Mono.empty()
    }

    private fun findChildNamesAndShortIdsByPhaseId(phaseId: UUID): Mono<List<Pair<String, Int>>> =
        repository.findByParentId(phaseId)
            .map { Pair(it.name, it.shortId) }
            .collectList()

    private fun findLinkElementsByPhaseId(phaseId: UUID): Mono<List<LinkElement>> =
        linkElementService.findByPhaseId(phaseId.toString())
            .collectList()
}