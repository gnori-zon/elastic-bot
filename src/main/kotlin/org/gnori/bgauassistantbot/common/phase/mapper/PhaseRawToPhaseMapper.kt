package org.gnori.bgauassistantbot.common.phase.mapper

import org.gnori.bgauassistantbot.common.mapper.Mapper
import org.gnori.bgauassistantbot.common.phase.model.Phase
import org.gnori.bgauassistantbot.common.phase.model.PhaseImpl
import org.springframework.stereotype.Component

@Component
class PhaseRawToPhaseMapper : Mapper<PhaseRaw, Phase> {

    override fun map(param: PhaseRaw): Phase =
        PhaseImpl(
            id = param.entity.id.toString(),
            name = param.entity.name,
            description = param.entity.description,
            shortId = param.entity.shortId,
            childNamesWithShortIds = param.childNamesWithShortIds,
            linkElements = param.linkElements,
        )
}