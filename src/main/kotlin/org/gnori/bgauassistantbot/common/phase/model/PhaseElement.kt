package org.gnori.bgauassistantbot.common.phase.model

import org.gnori.bgauassistantbot.common.displayelement.model.DisplayElement
import org.gnori.bgauassistantbot.common.linkelement.model.LinkElement

interface PhaseElement: PhaseContent {
    val id: String
    val name: String
    val description: String
    val linkElements: List<LinkElement>
    val displayElements: List<DisplayElement>
}