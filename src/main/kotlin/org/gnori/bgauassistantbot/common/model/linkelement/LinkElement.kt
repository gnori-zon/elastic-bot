package org.gnori.bgauassistantbot.common.model.linkelement

interface LinkElement {
    val id: String
    val name: String
    val link: String
    val type: LinkElementType
}