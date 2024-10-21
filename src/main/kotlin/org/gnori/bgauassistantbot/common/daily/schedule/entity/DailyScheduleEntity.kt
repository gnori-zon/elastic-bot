package org.gnori.bgauassistantbot.common.daily.schedule.entity
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDate
import java.util.*

@Entity
@Table(name = "daily_schedules")
class DailyScheduleEntity(
    @Id var id: UUID? = null,
    var weekDay: String,
    var date: LocalDate,
    var lessonsData: String,
    var groupName: String
) {
    constructor() : this(weekDay = "", date = LocalDate.now(), lessonsData = "", groupName = "")
}