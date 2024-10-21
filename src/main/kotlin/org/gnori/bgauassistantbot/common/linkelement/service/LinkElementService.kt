package org.gnori.bgauassistantbot.common.linkelement.service

import org.gnori.bgauassistantbot.common.linkelement.model.LinkElement

interface LinkElementService {
    fun findByPhaseId(phaseId: String): List<LinkElement>
    fun findById(id: String): LinkElement?
}