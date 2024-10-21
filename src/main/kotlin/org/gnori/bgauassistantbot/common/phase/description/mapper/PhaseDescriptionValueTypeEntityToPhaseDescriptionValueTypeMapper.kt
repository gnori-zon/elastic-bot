package org.gnori.bgauassistantbot.common.phase.description.mapper

import org.gnori.bgauassistantbot.common.mapper.Mapper
import org.gnori.bgauassistantbot.common.phase.description.entity.PhaseDescriptionValueTypeEntity
import org.gnori.bgauassistantbot.common.phase.description.model.PhaseDescriptionValueType
import org.springframework.stereotype.Component

@Component
class PhaseDescriptionValueTypeEntityToPhaseDescriptionValueTypeMapper
    : Mapper<PhaseDescriptionValueTypeEntity, PhaseDescriptionValueType> {
    override fun map(param: PhaseDescriptionValueTypeEntity): PhaseDescriptionValueType =
        PhaseDescriptionValueType.valueOf(param.name)
}