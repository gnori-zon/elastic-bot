package org.gnori.bgauassistantbot.common.named.query.service

import org.gnori.bgauassistantbot.common.named.query.model.CreateNamedQueryInputParam
import org.gnori.bgauassistantbot.common.named.query.model.NamedQueryInputParam

interface NamedQueryInputParamService {
    fun findBy(queryId: String): List<NamedQueryInputParam>
    fun createIfNotExist(queryId: String, createNamedQueryInputParam: CreateNamedQueryInputParam): NamedQueryInputParam?
}