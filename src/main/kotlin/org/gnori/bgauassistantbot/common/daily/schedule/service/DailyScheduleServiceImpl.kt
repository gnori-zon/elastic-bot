package org.gnori.bgauassistantbot.common.daily.schedule.service

import org.gnori.bgauassistantbot.common.daily.schedule.entity.DailyScheduleEntity
import org.gnori.bgauassistantbot.common.daily.schedule.model.CreateDailySchedule
import org.gnori.bgauassistantbot.common.daily.schedule.model.DailySchedule
import org.gnori.bgauassistantbot.common.daily.schedule.model.DailyScheduleImpl
import org.gnori.bgauassistantbot.common.daily.schedule.repository.DailyScheduleEntityRepository
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
class DailyScheduleServiceImpl(
    private val repository: DailyScheduleEntityRepository
) : DailyScheduleService {

    override fun createIfNotExist(createDailySchedule: CreateDailySchedule): DailySchedule? {

        val entity = repository.findByDateAndGroupName(createDailySchedule.date, createDailySchedule.groupName)
            ?: repository.saveAndFlush(
                DailyScheduleEntity(
                    weekDay = createDailySchedule.weekDay,
                    date = createDailySchedule.date,
                    lessonsData = createDailySchedule.lessonsData,
                    groupName = createDailySchedule.groupName
                )
            )

        return DailyScheduleImpl("${entity.id}", entity.weekDay, entity.date, entity.lessonsData, entity.groupName)
    }

    override fun delete(groupName: String): Boolean =
        repository.deleteByGroupName(groupName)

    override fun delete(groupName: String, maxDate: LocalDate): Boolean =
        repository.deleteByGroupNameAndDateLessThan(groupName, maxDate)

}