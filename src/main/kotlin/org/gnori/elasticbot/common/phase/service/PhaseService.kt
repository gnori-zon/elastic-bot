package org.gnori.elasticbot.common.phase.service

import org.gnori.elasticbot.common.phase.description.model.PhaseDescriptionType
import org.gnori.elasticbot.common.phase.model.CreatePhase
import org.gnori.elasticbot.common.phase.model.Phase

interface PhaseService {
    fun findById(id: String): Phase?
    fun findByShortId(id: Int, descriptionType: PhaseDescriptionType): Phase?
    fun findFirstPhase(descriptionType: PhaseDescriptionType): Phase?
    fun findByParentId(id: String): List<Phase>
    fun findParentByShortId(id: Int): Phase?
    fun create(createPhase: CreatePhase): Phase?
    fun delete(shortId: Int): Boolean
}