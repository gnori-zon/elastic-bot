package org.gnori.bgauassistantbot.common.phase.action.service

import org.gnori.bgauassistantbot.common.phase.action.model.CreatePhaseAction
import org.gnori.bgauassistantbot.common.phase.action.model.PhaseAction

interface PhaseActionService {
    fun findById(id: String): PhaseAction?
    fun findByShortId(shortId: Int): PhaseAction?
    fun findByPhaseId(phaseId: String): List<PhaseAction>
    fun create(createPhaseAction: CreatePhaseAction): PhaseAction?
}