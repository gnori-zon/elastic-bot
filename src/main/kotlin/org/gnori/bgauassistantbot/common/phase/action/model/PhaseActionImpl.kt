package org.gnori.bgauassistantbot.common.phase.action.model

import org.gnori.bgauassistantbot.common.named.query.model.NamedQuery

class PhaseActionImpl(
    override val id: String,
    override val shortId: Int,
    override val name: String,
    override val fromPhaseId: String,
    override val redirectPhaseShortId: Int,
    override val displayConditionNamedQuery: NamedQuery?,
    override val actionNamedQuery: NamedQuery
) : PhaseAction