package org.gnori.bgauassistantbot.common.linkelement.service

import org.gnori.bgauassistantbot.common.linkelement.mapper.LinkElementTypeEntityToLinkElementTypeMapper
import org.gnori.bgauassistantbot.common.linkelement.model.LinkElementType
import org.gnori.bgauassistantbot.common.linkelement.repository.LinkElementTypeEntityRepository
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import java.util.*

@Component
class LinkElementTypeServiceImpl(
    private val repository: LinkElementTypeEntityRepository,
    private val mapper: LinkElementTypeEntityToLinkElementTypeMapper
) : LinkElementTypeService {

    override fun findById(id: String): Mono<LinkElementType> =
        repository.findById(UUID.fromString(id))
            .map(mapper::map)
}