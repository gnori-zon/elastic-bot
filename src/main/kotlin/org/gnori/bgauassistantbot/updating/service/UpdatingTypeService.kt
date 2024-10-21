package org.gnori.bgauassistantbot.updating.service

import org.gnori.bgauassistantbot.updating.model.UpdatingType

interface UpdatingTypeService {
    fun findById(id: String): UpdatingType?
}