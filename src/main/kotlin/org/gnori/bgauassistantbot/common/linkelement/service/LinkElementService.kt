package org.gnori.bgauassistantbot.common.linkelement.service

import org.gnori.bgauassistantbot.common.linkelement.model.LinkElement
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface LinkElementService {
    fun findByPhaseId(phaseId: String): Flux<LinkElement>
    fun findById(id: String): Mono<LinkElement>
}