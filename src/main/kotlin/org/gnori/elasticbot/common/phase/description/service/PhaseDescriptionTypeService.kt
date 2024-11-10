package org.gnori.elasticbot.common.phase.description.service

import org.gnori.elasticbot.common.phase.description.model.PhaseDescriptionType

interface PhaseDescriptionTypeService {
    fun getId(type: PhaseDescriptionType) : String?
}