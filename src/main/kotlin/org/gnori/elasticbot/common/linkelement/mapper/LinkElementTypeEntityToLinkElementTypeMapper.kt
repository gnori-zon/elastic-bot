package org.gnori.elasticbot.common.linkelement.mapper

import org.gnori.elasticbot.common.linkelement.entity.LinkElementTypeEntity
import org.gnori.elasticbot.common.linkelement.model.LinkElementType
import org.gnori.elasticbot.common.mapper.Mapper
import org.springframework.stereotype.Component

@Component
class LinkElementTypeEntityToLinkElementTypeMapper : Mapper<LinkElementTypeEntity, LinkElementType> {

    override fun map(param: LinkElementTypeEntity): LinkElementType =
        LinkElementType.valueOf(param.name.uppercase())
}