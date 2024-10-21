package org.gnori.bgauassistantbot.common.daily.schedule.model

import java.time.LocalDate

class DailyScheduleImpl(
    override val id: String,
    override val weekDay: String,
    override val date: LocalDate,
    override val lessonsData: String,
    override val groupName: String
) : DailySchedule