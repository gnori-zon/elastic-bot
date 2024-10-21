package org.gnori.bgauassistantbot.common.phase.action.model

import org.gnori.bgauassistantbot.common.named.query.model.NamedQuery

interface PhaseAction {
    val id: String
    val shortId: Int
    val name: String
    val fromPhaseId: String
    val redirectPhaseShortId: Int
    val displayConditionNamedQuery: NamedQuery?
    val actionNamedQuery: NamedQuery
}