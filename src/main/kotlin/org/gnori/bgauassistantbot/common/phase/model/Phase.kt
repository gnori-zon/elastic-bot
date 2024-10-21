package org.gnori.bgauassistantbot.common.phase.model

import org.gnori.bgauassistantbot.common.linkelement.model.LinkElement
import org.gnori.bgauassistantbot.common.phase.action.model.PhaseAction
import org.gnori.bgauassistantbot.common.phase.description.model.PhaseDescriptionFormatType

interface Phase {
    val id: String
    val name: String
    val headerLinkElement: LinkElement?
    val description: String
    val descriptionFormatType: PhaseDescriptionFormatType
    val shortId: Int
    val childNamesWithShortIds: List<Pair<String, Int>>
    val linkElements: List<LinkElement>
    val actions: List<PhaseAction>
}