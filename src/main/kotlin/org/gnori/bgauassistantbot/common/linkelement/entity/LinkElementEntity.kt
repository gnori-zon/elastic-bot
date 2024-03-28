package org.gnori.bgauassistantbot.common.linkelement.entity

import java.util.UUID

class LinkElementEntity(
    val id: UUID,
    val name: String,
    val link: String,
    val typeId: UUID
)