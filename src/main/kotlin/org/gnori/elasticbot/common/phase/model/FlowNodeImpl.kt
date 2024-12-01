package org.gnori.elasticbot.common.phase.model

import org.gnori.elasticbot.common.linkelement.model.LinkElement
import org.gnori.elasticbot.common.phase.action.model.PhaseAction
import org.gnori.elasticbot.common.phase.description.model.PhaseDescriptionFormatType

class FlowNodeImpl(
    override val id: String,
    override val name: String,
    override val headerLinkElement: LinkElement?,
    override val description: String,
    override val descriptionFormatType: PhaseDescriptionFormatType,
    override val shortId: Int,
    override val childNamesWithShortIds: List<Pair<String, Int>> = emptyList(),
    override val linkElements: List<LinkElement> = emptyList(),
    override val actions: List<PhaseAction> = emptyList()
) : FlowNode