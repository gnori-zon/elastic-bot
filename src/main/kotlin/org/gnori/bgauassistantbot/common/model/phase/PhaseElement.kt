package org.gnori.bgauassistantbot.common.model.phase

import org.gnori.bgauassistantbot.common.model.displayelement.DisplayElement
import org.gnori.bgauassistantbot.common.model.linkelement.LinkElement

interface PhaseElement: PhaseContent {
    val id: String
    val name: String
    val description: String
    val linkElements: List<LinkElement>
    val displayElements: List<DisplayElement>
}