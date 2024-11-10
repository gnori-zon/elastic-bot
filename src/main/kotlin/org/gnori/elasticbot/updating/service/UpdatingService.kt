package org.gnori.elasticbot.updating.service

import org.gnori.elasticbot.updating.model.Updating
import org.gnori.elasticbot.updating.model.UpdatingType

interface UpdatingService {
    fun findByType(type: UpdatingType): List<Updating>
}