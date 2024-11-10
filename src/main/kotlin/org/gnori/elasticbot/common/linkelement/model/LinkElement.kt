package org.gnori.elasticbot.common.linkelement.model

interface LinkElement {
    val id: String
    val name: String
    val link: String
    val type: LinkElementType
}