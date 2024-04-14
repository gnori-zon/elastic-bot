package org.gnori.bgauassistantbot.common.linkelement.mapper

import org.gnori.bgauassistantbot.common.linkelement.model.LinkElement
import org.gnori.bgauassistantbot.common.linkelement.model.LinkElementImpl
import org.gnori.bgauassistantbot.common.mapper.Mapper
import org.springframework.stereotype.Component

@Component
class LinkElementRawToLinkElementMapper : Mapper<LinkElementRaw, LinkElement> {

    override fun map(param: LinkElementRaw): LinkElement =
        LinkElementImpl(
            id = param.entity.id.toString(),
            name = param.entity.name,
            link = param.entity.link,
            type = param.type
        )
}