package org.gnori.elasticbot.updating.repository

import org.gnori.elasticbot.updating.entity.UpdatingTypeEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UpdatingTypeEntityRepository: JpaRepository<UpdatingTypeEntity, UUID>