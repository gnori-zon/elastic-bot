package org.gnori.bgauassistantbot.common.daily.schedule.service

import org.gnori.bgauassistantbot.common.daily.schedule.model.CreateDailySchedule
import org.gnori.bgauassistantbot.common.daily.schedule.model.DailySchedule
import java.time.LocalDate

interface DailyScheduleService {
    fun createIfNotExist(createDailySchedule: CreateDailySchedule): DailySchedule?
    fun delete(groupName: String): Boolean
    fun delete(groupName: String, maxDate: LocalDate): Boolean
}