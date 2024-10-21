package org.gnori.bgauassistantbot.common.daily.schedule.model

import java.time.LocalDate

interface DailySchedule {
    val id: String
    val weekDay: String
    val date: LocalDate
    val lessonsData: String
    val groupName: String
}