package org.gnori.bgauassistantbot.common.model.linkelement

data class LinkElementImpl(
    override val id: String,
    override val name: String,
    override val link: String,
    override val type: LinkElementType
): LinkElement
