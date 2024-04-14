package org.gnori.bgauassistantbot.common.linkelement.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.util.*

@Table(name = "link_element_types")
class LinkElementTypeEntity(
    @Id val id: UUID,
    val name: String
)