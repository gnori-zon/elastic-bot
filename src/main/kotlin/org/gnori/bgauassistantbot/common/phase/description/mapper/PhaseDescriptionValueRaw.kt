package org.gnori.bgauassistantbot.common.phase.description.mapper

import org.gnori.bgauassistantbot.common.phase.description.model.PhaseDescriptionValueType
import java.util.*

class PhaseDescriptionValueRaw(
    val id: UUID,
    val type: PhaseDescriptionValueType,
    val text: String
)