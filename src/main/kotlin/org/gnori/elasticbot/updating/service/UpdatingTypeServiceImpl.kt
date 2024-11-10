package org.gnori.elasticbot.updating.service

import org.gnori.elasticbot.updating.model.UpdatingType
import org.gnori.elasticbot.updating.repository.UpdatingTypeEntityRepository
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