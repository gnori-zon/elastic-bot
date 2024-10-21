package org.gnori.bgauassistantbot.common.phase.description.model

interface PhaseDescription {
    val id: String
    val type: PhaseDescriptionType
    val text: String
    val formatType: PhaseDescriptionFormatType
}