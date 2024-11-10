package org.gnori.elasticbot.common.linkelement.model

class LinkElementImpl(
    override val id: String,
    override val name: String,
    override val link: String,
    override val type: LinkElementType
) : LinkElement
