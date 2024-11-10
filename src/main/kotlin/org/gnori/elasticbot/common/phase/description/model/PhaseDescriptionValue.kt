package org.gnori.elasticbot.common.phase.description.model

interface PhaseDescriptionValue {
    val id: String
    val type: PhaseDescriptionValueType
    val text: String
}