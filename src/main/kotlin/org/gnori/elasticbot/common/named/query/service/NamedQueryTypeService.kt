package org.gnori.elasticbot.common.named.query.service

import org.gnori.elasticbot.common.named.query.model.NamedQueryType

interface NamedQueryTypeService {
    fun findBy(id: String) : NamedQueryType?
    fun getId(type: NamedQueryType): String?
}