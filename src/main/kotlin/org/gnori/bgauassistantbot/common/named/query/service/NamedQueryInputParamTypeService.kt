package org.gnori.bgauassistantbot.common.named.query.service

import org.gnori.bgauassistantbot.common.named.query.model.NamedQueryInputParamType

interface NamedQueryInputParamTypeService {
    fun findBy(id: String): NamedQueryInputParamType?
    fun getId(type: NamedQueryInputParamType): String?
}