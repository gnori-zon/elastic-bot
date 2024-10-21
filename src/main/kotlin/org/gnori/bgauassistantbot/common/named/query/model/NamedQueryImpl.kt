package org.gnori.bgauassistantbot.common.named.query.model

class NamedQueryImpl (
    override val id: String,
    override val name: String,
    override val query: String,
    override val type: NamedQueryType,
    override val params: List<NamedQueryInputParam> = emptyList()
): NamedQuery