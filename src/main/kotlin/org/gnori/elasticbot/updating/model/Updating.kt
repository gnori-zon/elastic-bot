package org.gnori.elasticbot.updating.model

import org.gnori.elasticbot.common.phase.model.Phase

interface Updating {
    val id: String
    val type: UpdatingType
    val parentPhase: Phase
}