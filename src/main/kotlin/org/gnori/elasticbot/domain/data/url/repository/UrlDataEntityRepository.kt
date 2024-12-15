package org.gnori.elasticbot.domain.data.url.repository

import org.gnori.elasticbot.domain.data.url.entity.UrlDataEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface UrlDataEntityRepository : JpaRepository<UrlDataEntity, UUID>