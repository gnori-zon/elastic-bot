package org.gnori.bgauassistantbot.common.named.query.service

import org.gnori.bgauassistantbot.common.named.query.model.NamedQueryType
import org.gnori.bgauassistantbot.common.named.query.repository.NamedQueryTypeEntityRepository
import org.springframework.stereotype.Component
import java.util.*

@Component
class NamedQueryTypeServiceImpl(
    private val repository: NamedQueryTypeEntityRepository
) : NamedQueryTypeService {

    override fun findBy(id: String) : NamedQueryType? =
        repository.findById(UUID.fromString(id)).orElse(null)
            ?.let { NamedQueryType.valueOf(it.name) }

    override fun getId(type: NamedQueryType): String? =
        repository.findByName(type.name)
            ?.let { "${it.id}"}
}