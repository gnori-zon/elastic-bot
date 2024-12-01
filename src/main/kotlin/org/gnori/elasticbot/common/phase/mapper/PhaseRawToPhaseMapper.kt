package org.gnori.elasticbot.common.phase.mapper

import org.gnori.elasticbot.common.mapper.Mapper
import org.gnori.elasticbot.common.phase.model.FlowNode
import org.gnori.elasticbot.common.phase.model.FlowNodeImpl
import org.springframework.stereotype.Component

@Component
class PhaseRawToPhaseMapper : Mapper<PhaseRaw, FlowNode> {

    override fun map(param: PhaseRaw): FlowNode =
        FlowNodeImpl(
            id = param.entity.id.toString(),
            name = param.entity.name,
            headerLinkElement = param.headerLinkElement,
            description = param.description.text,
            descriptionFormatType = param.description.formatType,
            shortId = param.entity.shortId!!,
            childNamesWithShortIds = param.childNamesWithShortIds,
            linkElements = param.linkElements,
            actions = param.actions
        )
}