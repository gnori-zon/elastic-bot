package org.gnori.bgauassistantbot.updating.model

import org.gnori.bgauassistantbot.common.phase.model.Phase

class UpdatingImpl(
    override val id: String,
    override val type: UpdatingType,
    override val parentPhase: Phase
) : Updating