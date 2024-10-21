package org.gnori.bgauassistantbot.common.named.query.service

import org.gnori.bgauassistantbot.common.named.query.model.NamedQueryType

interface NamedQueryTypeService {
    fun findBy(id: String) : NamedQueryType?
    fun getId(type: NamedQueryType): String?
}