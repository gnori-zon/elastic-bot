package org.gnori.bgauassistantbot.common.phase.description.service

import org.gnori.bgauassistantbot.common.phase.description.model.PhaseDescriptionType

interface PhaseDescriptionTypeService {
    fun getId(type: PhaseDescriptionType) : String?
}