package org.gnori.bgauassistantbot.common.phase.description.service

import org.gnori.bgauassistantbot.common.phase.description.model.CreatePhaseDescription
import org.gnori.bgauassistantbot.common.phase.description.model.PhaseDescription
import org.gnori.bgauassistantbot.common.phase.description.model.PhaseDescriptionType

interface PhaseDescriptionService {
    fun findBy(phaseId: String, type: PhaseDescriptionType): PhaseDescription?
    fun createIfNotExist(createPhaseDescription: CreatePhaseDescription): String?
}