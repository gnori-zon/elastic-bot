package org.gnori.elasticbot.updating.service

import org.gnori.elasticbot.updating.model.UpdatingType

interface UpdatingTypeService {
    fun findById(id: String): UpdatingType?
}