package org.gnori.elasticbot.common.phase.action.model

class CreatePhaseAction(
    val phaseId: String,
    val redirectPhaseId: String? = null,
    val name: String,
    val displayConditionNamedQueryId: String,
    val actionNamedQueryId: String,
)