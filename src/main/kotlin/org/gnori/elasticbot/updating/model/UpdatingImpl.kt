package org.gnori.elasticbot.updating.model

import org.gnori.elasticbot.common.phase.model.FlowNode

class UpdatingImpl(
    override val id: String,
    override val type: UpdatingType,
    override val parentPhase: FlowNode
) : Updating