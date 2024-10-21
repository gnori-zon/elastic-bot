package org.gnori.bgauassistantbot.common.phase.description.service

import org.gnori.bgauassistantbot.common.phase.description.model.PhaseDescriptionFormatType

interface PhaseDescriptionFormatTypeService {
    fun findById(id: String): PhaseDescriptionFormatType?
    fun getId(type: PhaseDescriptionFormatType): String?
}