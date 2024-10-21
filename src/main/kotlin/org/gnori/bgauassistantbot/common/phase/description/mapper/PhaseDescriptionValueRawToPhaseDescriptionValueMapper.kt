package org.gnori.bgauassistantbot.common.phase.description.mapper

import org.gnori.bgauassistantbot.common.mapper.Mapper
import org.gnori.bgauassistantbot.common.phase.description.model.PhaseDescriptionValue
import org.gnori.bgauassistantbot.common.phase.description.model.PhaseDescriptionValueImpl
import org.springframework.stereotype.Component

@Component
class PhaseDescriptionValueRawToPhaseDescriptionValueMapper : Mapper<PhaseDescriptionValueRaw, PhaseDescriptionValue> {
    override fun map(param: PhaseDescriptionValueRaw): PhaseDescriptionValue =
        PhaseDescriptionValueImpl("${param.id}", param.type, param.text)
}