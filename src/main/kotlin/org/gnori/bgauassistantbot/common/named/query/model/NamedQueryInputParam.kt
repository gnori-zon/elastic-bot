package org.gnori.bgauassistantbot.common.named.query.model

interface NamedQueryInputParam {
    val id: String
    val type: NamedQueryInputParamType
    val name: String
    val value: String
}