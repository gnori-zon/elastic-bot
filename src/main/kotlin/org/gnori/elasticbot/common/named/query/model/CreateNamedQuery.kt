package org.gnori.elasticbot.common.named.query.model

class CreateNamedQuery(
    val name: String,
    val query: String,
    val type: NamedQueryType,
    val inputParams: List<CreateNamedQueryInputParam>
)