package org.gnori.bgauassistantbot.common.phase.description.model

class PhaseDescriptionValueImpl(
    override val id: String,
    override val type: PhaseDescriptionValueType,
    override val text: String
): PhaseDescriptionValue
