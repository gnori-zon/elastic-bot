package org.gnori.bgauassistantbot.common.phase.service

import org.gnori.bgauassistantbot.common.phase.model.Phase
import reactor.core.publisher.Mono

interface PhaseService {
    fun findByShortId(id: Int): Mono<Phase>
    fun findFirstPhase(): Mono<Phase>
    fun findParentByShortId(id: Int): Mono<Phase>
}