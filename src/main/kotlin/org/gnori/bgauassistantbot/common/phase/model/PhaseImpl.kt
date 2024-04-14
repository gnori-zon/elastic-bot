package org.gnori.bgauassistantbot.common.phase.model

import org.gnori.bgauassistantbot.common.linkelement.model.LinkElement

data class PhaseImpl(
    override val id: String,
    override val name: String,
    override val description: String,
    override val childNames: List<String> = emptyList(),
    override val linkElements: List<LinkElement> = emptyList()
) : Phase