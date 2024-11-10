package org.gnori.elasticbot.common.linkelement.service

import org.gnori.elasticbot.common.linkelement.model.LinkElementType

interface LinkElementTypeService {
    fun findById(id: String): LinkElementType?
}