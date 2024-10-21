package org.gnori.bgauassistantbot.updating.model

import org.gnori.bgauassistantbot.common.phase.model.Phase

interface Updating {
    val id: String
    val type: UpdatingType
    val parentPhase: Phase
}