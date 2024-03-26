package org.gnori.bgauassistantbot.common.model.phase

interface Phase<T: PhaseContent>: PhaseContent {
    val id: String
    val name: String
    val description: String
    val phaseContents: List<T>
}