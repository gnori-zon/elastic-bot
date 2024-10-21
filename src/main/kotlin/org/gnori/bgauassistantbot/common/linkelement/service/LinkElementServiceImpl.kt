package org.gnori.bgauassistantbot.common.linkelement.service

import org.gnori.bgauassistantbot.common.linkelement.entity.LinkElementEntity
import org.gnori.bgauassistantbot.common.linkelement.mapper.LinkElementRaw
import org.gnori.bgauassistantbot.common.linkelement.mapper.LinkElementRawToLinkElementMapper
import org.gnori.bgauassistantbot.common.linkelement.model.LinkElement
import org.gnori.bgauassistantbot.common.linkelement.repository.LinkElementEntityRepository
import org.springframework.stereotype.Component
import java.util.*

@Component
class LinkElementServiceImpl(
    private val repository: LinkElementEntityRepository,
    private val linkElementTypeService: LinkElementTypeService,
    private val mapper: LinkElementRawToLinkElementMapper
) : LinkElementService {

    override fun findByPhaseId(phaseId: String): List<LinkElement> =
        repository.findByPhaseId(UUID.fromString(phaseId))
            .mapNotNull(this::findTypeAndMap)

    override fun findById(id: String): LinkElement? {
        return repository.findById(UUID.fromString(id)).orElse(null)
            ?.let { this.findTypeAndMap(it) }
    }

    private fun findTypeAndMap(entity: LinkElementEntity): LinkElement? =
        linkElementTypeService.findById(entity.typeId.toString())
            ?.let { type -> LinkElementRaw(entity, type) }
            ?.let(mapper::map)
}
