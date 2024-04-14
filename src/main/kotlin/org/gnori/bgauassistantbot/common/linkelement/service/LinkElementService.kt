package org.gnori.bgauassistantbot.common.linkelement.service

import org.gnori.bgauassistantbot.common.linkelement.model.LinkElement
import reactor.core.publisher.Flux

interface LinkElementService {
    fun findByPhaseId(phaseId: String): Flux<LinkElement>
}