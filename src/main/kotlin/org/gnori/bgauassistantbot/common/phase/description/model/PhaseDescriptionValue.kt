package org.gnori.bgauassistantbot.common.phase.description.model

interface PhaseDescriptionValue {
    val id: String
    val type: PhaseDescriptionValueType
    val text: String
}