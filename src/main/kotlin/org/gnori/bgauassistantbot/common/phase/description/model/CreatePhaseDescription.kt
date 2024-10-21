package org.gnori.bgauassistantbot.common.phase.description.model

class CreatePhaseDescription(
    val phaseId: String,
    val type: PhaseDescriptionType,
    val value: String,
    val valueType: PhaseDescriptionValueType,
    val formatType: PhaseDescriptionFormatType
)