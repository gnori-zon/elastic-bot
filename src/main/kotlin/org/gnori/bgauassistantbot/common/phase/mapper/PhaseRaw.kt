package org.gnori.bgauassistantbot.common.phase.mapper

import org.gnori.bgauassistantbot.common.linkelement.model.LinkElement
import org.gnori.bgauassistantbot.common.phase.entity.PhaseEntity

class PhaseRaw(
    val entity: PhaseEntity,
    val headerLinkElement: LinkElement? = null,
    val childNamesWithShortIds: List<Pair<String, Int>> = emptyList(),
    val linkElements: List<LinkElement> = emptyList()
)