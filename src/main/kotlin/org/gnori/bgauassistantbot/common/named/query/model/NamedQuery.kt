package org.gnori.bgauassistantbot.common.named.query.model

interface NamedQuery {
    val id: String
    val name: String
    val query: String
    val type: NamedQueryType
    val params: List<NamedQueryInputParam>
}