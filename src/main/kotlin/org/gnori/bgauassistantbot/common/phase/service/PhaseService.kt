package org.gnori.bgauassistantbot.common.phase.service

import org.gnori.bgauassistantbot.common.phase.model.Phase
import reactor.core.publisher.Mono

interface PhaseService {
    fun findByName(name: String): Mono<Phase>
}