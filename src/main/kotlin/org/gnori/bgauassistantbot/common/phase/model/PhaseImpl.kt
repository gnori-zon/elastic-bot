package org.gnori.bgauassistantbot.common.phase.model

import org.gnori.bgauassistantbot.common.linkelement.model.LinkElement
import org.gnori.bgauassistantbot.common.phase.action.model.PhaseAction
import org.gnori.bgauassistantbot.common.phase.description.model.PhaseDescriptionFormatType

class PhaseImpl(
    override val id: String,
    override val name: String,
    override val headerLinkElement: LinkElement?,
    override val description: String,
    override val descriptionFormatType: PhaseDescriptionFormatType,
    override val shortId: Int,
    override val childNamesWithShortIds: List<Pair<String, Int>> = emptyList(),
    override val linkElements: List<LinkElement> = emptyList(),
    override val actions: List<PhaseAction> = emptyList()
) : Phase