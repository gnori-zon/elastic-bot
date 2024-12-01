package org.gnori.elasticbot.common.phase.service

import org.gnori.elasticbot.common.phase.model.CreatePhase
import org.gnori.elasticbot.common.phase.model.FlowNode

interface FlowNodeService {
    fun findById(id: String): FlowNode?
    fun findStart(): FlowNode?
    fun create(createPhase: CreatePhase): FlowNode?
    fun delete(id: String)
}