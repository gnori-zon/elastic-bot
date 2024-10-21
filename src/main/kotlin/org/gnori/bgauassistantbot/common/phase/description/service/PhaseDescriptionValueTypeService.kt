package org.gnori.bgauassistantbot.common.phase.description.service

import org.gnori.bgauassistantbot.common.phase.description.model.PhaseDescriptionValueType

interface PhaseDescriptionValueTypeService {
    fun findBy(id: String): PhaseDescriptionValueType?
    fun getId(valueType: PhaseDescriptionValueType): String?
}