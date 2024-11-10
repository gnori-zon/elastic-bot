package org.gnori.elasticbot.common.named.query.model

interface NamedQueryInputParam {
    val id: String
    val type: NamedQueryInputParamType
    val name: String
    val value: String
}