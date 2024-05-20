package org.gnori.bgauassistantbot.common.phase.model

import org.gnori.bgauassistantbot.common.linkelement.model.LinkElement

class PhaseImpl(
    override val id: String,
    override val name: String,
    override val description: String,
    override val shortId: Int,
    override val childNamesWithShortIds: List<Pair<String, Int>> = emptyList(),
    override val linkElements: List<LinkElement> = emptyList()
) : Phase