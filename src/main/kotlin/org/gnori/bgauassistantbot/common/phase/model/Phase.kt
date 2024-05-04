package org.gnori.bgauassistantbot.common.phase.model

import org.gnori.bgauassistantbot.common.linkelement.model.LinkElement

interface Phase {
    val id: String
    val name: String
    val description: String
    val shortId: Long
    val childNames: List<String>
    val linkElements: List<LinkElement>
}