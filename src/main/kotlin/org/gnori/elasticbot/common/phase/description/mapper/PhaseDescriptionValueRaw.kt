package org.gnori.elasticbot.common.phase.description.mapper

import org.gnori.elasticbot.common.phase.description.model.PhaseDescriptionValueType
import java.util.*

class PhaseDescriptionValueRaw(
    val id: UUID,
    val type: PhaseDescriptionValueType,
    val text: String
)