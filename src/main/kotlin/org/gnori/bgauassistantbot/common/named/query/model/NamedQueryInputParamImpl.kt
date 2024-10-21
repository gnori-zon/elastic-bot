package org.gnori.bgauassistantbot.common.named.query.model

class NamedQueryInputParamImpl(
    override val id: String,
    override val type: NamedQueryInputParamType,
    override val name: String,
    override val value: String
) : NamedQueryInputParam