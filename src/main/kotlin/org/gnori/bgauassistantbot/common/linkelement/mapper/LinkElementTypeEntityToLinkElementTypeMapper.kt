package org.gnori.bgauassistantbot.common.linkelement.mapper

import org.gnori.bgauassistantbot.common.linkelement.entity.LinkElementTypeEntity
import org.gnori.bgauassistantbot.common.linkelement.model.LinkElementType
import org.gnori.bgauassistantbot.common.mapper.Mapper
import org.springframework.stereotype.Component

@Component
class LinkElementTypeEntityToLinkElementTypeMapper : Mapper<LinkElementTypeEntity, LinkElementType> {

    override fun map(param: LinkElementTypeEntity): LinkElementType =
        LinkElementType.valueOf(param.name.uppercase())
}