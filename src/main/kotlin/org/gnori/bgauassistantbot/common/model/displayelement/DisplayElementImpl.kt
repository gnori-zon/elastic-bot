package org.gnori.bgauassistantbot.common.model.displayelement

data class DisplayElementImpl(
    override val id: String,
    override val name: String,
    override val type: DisplayElementType
): DisplayElement
