package org.gnori.elasticbot.common.phase.description.service

import org.gnori.elasticbot.common.phase.description.model.CreatePhaseDescriptionValue
import org.gnori.elasticbot.common.phase.description.model.PhaseDescriptionValue

interface  PhaseDescriptionValueService {
    fun findBy(id: String, fieldsContext: Map<String, Any>): PhaseDescriptionValue?
    fun createIfNotExist(createPhaseDescriptionValue: CreatePhaseDescriptionValue): String?
}