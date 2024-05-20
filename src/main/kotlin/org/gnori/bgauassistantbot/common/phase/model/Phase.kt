package org.gnori.bgauassistantbot.common.phase.model

import org.gnori.bgauassistantbot.common.linkelement.model.LinkElement

interface Phase {
    val id: String
    val name: String
    val description: String
    val shortId: Int
    val childNamesWithShortIds: List<Pair<String, Int>>
    val linkElements: List<LinkElement>
}