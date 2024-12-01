package org.gnori.elasticbot.common.phase.mapper

import org.gnori.elasticbot.common.linkelement.model.LinkElement
import org.gnori.elasticbot.common.phase.action.model.PhaseAction
import org.gnori.elasticbot.common.phase.description.model.PhaseDescription
import org.gnori.elasticbot.common.phase.entity.FlowNodeEntity

class PhaseRaw(
    val entity: FlowNodeEntity,
    val description: PhaseDescription,
    val headerLinkElement: LinkElement? = null,
    val childNamesWithShortIds: List<Pair<String, Int>> = emptyList(),
    val linkElements: List<LinkElement> = emptyList(),
    val actions: List<PhaseAction> = emptyList()
)