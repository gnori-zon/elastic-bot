package org.gnori.bgauassistantbot.updating.service

import org.gnori.bgauassistantbot.updating.model.Updating
import org.gnori.bgauassistantbot.updating.model.UpdatingType

interface UpdatingService {
    fun findByType(type: UpdatingType): List<Updating>
}