package org.gnori.bgauassistantbot.common.phase.description.model

class PhaseDescriptionImpl(
    override val id: String,
    override val type: PhaseDescriptionType,
    override val text: String,
    override val formatType: PhaseDescriptionFormatType = PhaseDescriptionFormatType.NULL
): PhaseDescription