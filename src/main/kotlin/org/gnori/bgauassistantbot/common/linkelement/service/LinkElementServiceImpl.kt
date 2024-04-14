package org.gnori.bgauassistantbot.common.linkelement.service

import org.gnori.bgauassistantbot.common.linkelement.entity.LinkElementEntity
import org.gnori.bgauassistantbot.common.linkelement.mapper.LinkElementRaw
import org.gnori.bgauassistantbot.common.linkelement.mapper.LinkElementRawToLinkElementMapper
import org.gnori.bgauassistantbot.common.linkelement.model.LinkElement
import org.gnori.bgauassistantbot.common.linkelement.repository.LinkElementEntityRepository
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.*

@Component
class LinkElementServiceImpl(
    private val repository: LinkElementEntityRepository,
    private val linkElementTypeService: LinkElementTypeService,
    private val mapper: LinkElementRawToLinkElementMapper
) : LinkElementService {

    override fun findByPhaseId(phaseId: String): Flux<LinkElement> =
        repository.findByPhaseId(UUID.fromString(phaseId))
            .flatMap(this::findTypeAndMap)

    private fun findTypeAndMap(entity: LinkElementEntity): Mono<LinkElement> =
        linkElementTypeService.findById(entity.typeId.toString())
            .map { type -> LinkElementRaw(entity, type) }
            .map(mapper::map)
}
