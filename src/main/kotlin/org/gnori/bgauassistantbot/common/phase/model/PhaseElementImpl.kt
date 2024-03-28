package org.gnori.bgauassistantbot.common.phase.model

import org.gnori.bgauassistantbot.common.displayelement.model.DisplayElement
import org.gnori.bgauassistantbot.common.linkelement.model.LinkElement

data class PhaseElementImpl(
    override val id: String,
    override val name: String,
    override val description: String,
    override val linkElements: List<LinkElement> = emptyList(),
    override val displayElements: List<DisplayElement> = emptyList(),
): PhaseElement