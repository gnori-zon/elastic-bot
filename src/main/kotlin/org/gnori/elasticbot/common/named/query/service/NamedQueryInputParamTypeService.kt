package org.gnori.elasticbot.common.named.query.service

import org.gnori.elasticbot.common.named.query.model.NamedQueryInputParamType

interface NamedQueryInputParamTypeService {
    fun findBy(id: String): NamedQueryInputParamType?
    fun getId(type: NamedQueryInputParamType): String?
}