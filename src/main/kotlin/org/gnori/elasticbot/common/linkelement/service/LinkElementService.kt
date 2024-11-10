package org.gnori.elasticbot.common.linkelement.service

import org.gnori.elasticbot.common.linkelement.model.LinkElement

interface LinkElementService {
    fun findByPhaseId(phaseId: String): List<LinkElement>
    fun findById(id: String): LinkElement?
}