package org.gnori.bgauassistantbot.common.linkelement.service

import org.gnori.bgauassistantbot.common.linkelement.model.LinkElementType

interface LinkElementTypeService {
    fun findById(id: String): LinkElementType?
}