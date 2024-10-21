package org.gnori.bgauassistantbot.common.daily.schedule.repository

import org.gnori.bgauassistantbot.common.daily.schedule.entity.DailyScheduleEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.time.LocalDate
import java.util.*

@Repository
interface DailyScheduleEntityRepository : JpaRepository<DailyScheduleEntity, UUID> {
    fun findByDateAndGroupName(date: LocalDate, groupName: String): DailyScheduleEntity?
    fun deleteByGroupName(groupName: String): Boolean
    fun deleteByGroupNameAndDateLessThan(groupName: String, minDate: LocalDate): Boolean
}