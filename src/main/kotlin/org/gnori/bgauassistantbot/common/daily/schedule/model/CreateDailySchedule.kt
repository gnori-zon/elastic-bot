package org.gnori.bgauassistantbot.common.daily.schedule.model

import java.time.LocalDate

class CreateDailySchedule(
    val weekDay: String,
    val date: LocalDate,
    val lessonsData: String,
    val groupName: String
)