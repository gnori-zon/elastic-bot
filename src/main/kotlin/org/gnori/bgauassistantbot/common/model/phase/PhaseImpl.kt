package org.gnori.bgauassistantbot.common.model.phase

data class PhaseImpl<T : PhaseContent>(
    override val id: String,
    override val name: String,
    override val description: String,
    override val phaseContents: List<T> = emptyList()
) : Phase<T>