package org.gnori.bgauassistantbot.updating.service

import org.gnori.bgauassistantbot.updating.model.UpdatingType
import org.gnori.bgauassistantbot.updating.repository.UpdatingTypeEntityRepository
import org.springframework.stereotype.Component
import java.util.*

@Component
class UpdatingTypeServiceImpl(
    private val repository: UpdatingTypeEntityRepository
) : UpdatingTypeService {

    override fun findById(id: String): UpdatingType? =
        repository.findById(UUID.fromString(id)).orElse(null)
            ?.let { UpdatingType.valueOf(it.name) }
}