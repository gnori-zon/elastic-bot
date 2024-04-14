package org.gnori.bgauassistantbot.common.phase.mapper

import org.gnori.bgauassistantbot.common.linkelement.model.LinkElement
import org.gnori.bgauassistantbot.common.phase.entity.PhaseEntity

class PhaseRaw(
    val entity: PhaseEntity,
    val childNames: List<String> = emptyList(),
    val linkElements: List<LinkElement> = emptyList()
)