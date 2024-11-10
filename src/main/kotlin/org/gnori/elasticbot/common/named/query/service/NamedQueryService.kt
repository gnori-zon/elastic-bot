package org.gnori.elasticbot.common.named.query.service

import org.gnori.elasticbot.common.named.query.model.CreateNamedQuery
import org.gnori.elasticbot.common.named.query.model.NamedQuery

interface NamedQueryService {
    fun findBy(id: String): NamedQuery?
    fun executeQueryBy(id: String, fieldsContext: Map<String, Any>): List<Map<String, Any>>
    fun execute(namedQuery: NamedQuery, fieldsContext: Map<String, Any>): List<Map<String, Any>>
    fun createIfNotExist(createNamedQuery: CreateNamedQuery): NamedQuery?
}