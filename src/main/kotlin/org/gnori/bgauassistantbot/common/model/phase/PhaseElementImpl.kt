package org.gnori.bgauassistantbot.common.model.phase

import org.gnori.bgauassistantbot.common.model.displayelement.DisplayElement
import org.gnori.bgauassistantbot.common.model.linkelement.LinkElement

data class PhaseElementImpl(
    override val id: String,
    override val name: String,
    override val description: String,
    override val linkElements: List<LinkElement> = emptyList(),
    override val displayElements: List<DisplayElement> = emptyList(),
): PhaseElement