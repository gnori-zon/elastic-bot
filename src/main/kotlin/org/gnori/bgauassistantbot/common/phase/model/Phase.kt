package org.gnori.bgauassistantbot.common.phase.model

interface Phase<T: PhaseContent>: PhaseContent {
    val id: String
    val name: String
    val description: String
    val phaseContents: List<T>
}