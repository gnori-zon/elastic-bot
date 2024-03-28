package org.gnori.bgauassistantbot.common.linkelement.model

data class LinkElementImpl(
    override val id: String,
    override val name: String,
    override val link: String,
    override val type: LinkElementType
): LinkElement
