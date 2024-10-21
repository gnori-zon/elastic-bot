package org.gnori.bgauassistantbot.common.phase.mapper

import org.gnori.bgauassistantbot.common.linkelement.model.LinkElement
import org.gnori.bgauassistantbot.common.phase.action.model.PhaseAction
import org.gnori.bgauassistantbot.common.phase.description.model.PhaseDescription
import org.gnori.bgauassistantbot.common.phase.entity.PhaseEntity

class PhaseRaw(
    val entity: PhaseEntity,
    val description: PhaseDescription,
    val headerLinkElement: LinkElement? = null,
    val childNamesWithShortIds: List<Pair<String, Int>> = emptyList(),
    val linkElements: List<LinkElement> = emptyList(),
    val actions: List<PhaseAction> = emptyList()
)