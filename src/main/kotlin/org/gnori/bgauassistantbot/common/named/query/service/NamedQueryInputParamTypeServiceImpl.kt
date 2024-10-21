package org.gnori.bgauassistantbot.common.named.query.service

import org.gnori.bgauassistantbot.common.named.query.model.NamedQueryInputParamType
import org.gnori.bgauassistantbot.common.named.query.repository.NamedQueryInputParamTypeEntityRepository
import org.springframework.stereotype.Component
import java.util.*

@Component
class NamedQueryInputParamTypeServiceImpl(
    private val repository: NamedQueryInputParamTypeEntityRepository
) : NamedQueryInputParamTypeService {

    override fun findBy(id: String): NamedQueryInputParamType? =
        repository.findById(UUID.fromString(id)).orElse(null)
            ?.let { NamedQueryInputParamType.valueOf(it.name) }

    override fun getId(type: NamedQueryInputParamType): String? =
        repository.findByName(type.name)
            ?.let { "${it.id}" }
}