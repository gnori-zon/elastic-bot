package org.gnori.bgauassistantbot.common.linkelement.service

import org.gnori.bgauassistantbot.common.linkelement.model.LinkElementType
import reactor.core.publisher.Mono

interface LinkElementTypeService {
    fun findById(id: String): Mono<LinkElementType>
}