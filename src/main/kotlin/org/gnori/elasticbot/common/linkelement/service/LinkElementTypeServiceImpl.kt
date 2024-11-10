package org.gnori.elasticbot.common.linkelement.service

import org.gnori.elasticbot.common.linkelement.mapper.LinkElementTypeEntityToLinkElementTypeMapper
import org.gnori.elasticbot.common.linkelement.model.LinkElementType
import org.gnori.elasticbot.common.linkelement.repository.LinkElementTypeEntityRepository
import org.springframework.stereotype.Component
import java.util.*

@Component
class LinkElementTypeServiceImpl(
    private val repository: LinkElementTypeEntityRepository,
    private val mapper: LinkElementTypeEntityToLinkElementTypeMapper
) : LinkElementTypeService {

    override fun findById(id: String): LinkElementType? =
        repository.findById(UUID.fromString(id)).orElse(null)
            ?.let(mapper::map)
}