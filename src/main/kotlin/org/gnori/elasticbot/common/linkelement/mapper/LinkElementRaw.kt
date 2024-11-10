package org.gnori.elasticbot.common.linkelement.mapper

import org.gnori.elasticbot.common.linkelement.entity.LinkElementEntity
import org.gnori.elasticbot.common.linkelement.model.LinkElementType

class LinkElementRaw(
    val entity: LinkElementEntity,
    val type: LinkElementType
)