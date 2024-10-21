package org.gnori.bgauassistantbot.schedule

import org.gnori.bgauassistantbot.common.daily.schedule.model.CreateDailySchedule
import org.gnori.bgauassistantbot.common.daily.schedule.service.DailyScheduleService
import org.gnori.bgauassistantbot.common.ext.notContains
import org.gnori.bgauassistantbot.common.named.query.model.CreateNamedQuery
import org.gnori.bgauassistantbot.common.named.query.model.CreateNamedQueryInputParam
import org.gnori.bgauassistantbot.common.named.query.model.NamedQueryInputParamType
import org.gnori.bgauassistantbot.common.named.query.model.NamedQueryType
import org.gnori.bgauassistantbot.common.named.query.service.NamedQueryService
import org.gnori.bgauassistantbot.common.phase.action.model.CreatePhaseAction
import org.gnori.bgauassistantbot.common.phase.action.service.PhaseActionService
import org.gnori.bgauassistantbot.common.phase.description.model.CreatePhaseDescription
import org.gnori.bgauassistantbot.common.phase.description.model.PhaseDescriptionFormatType
import org.gnori.bgauassistantbot.common.phase.description.model.PhaseDescriptionType
import org.gnori.bgauassistantbot.common.phase.description.model.PhaseDescriptionValueType
import org.gnori.bgauassistantbot.common.phase.description.service.PhaseDescriptionService
import org.gnori.bgauassistantbot.common.phase.model.CreatePhase
import org.gnori.bgauassistantbot.common.phase.service.PhaseService
import org.gnori.bgauassistantbot.updating.model.UpdatingType
import org.gnori.bgauassistantbot.updating.parser.Parser
import org.gnori.bgauassistantbot.updating.parser.html.data.HtmlParseRequest
import org.gnori.bgauassistantbot.updating.parser.html.data.HtmlParseResponse
import org.gnori.bgauassistantbot.updating.service.UpdatingService
import org.springframework.http.HttpMethod
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import org.springframework.util.LinkedMultiValueMap
import org.springframework.web.reactive.function.BodyInserters
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Component
class ScheduledScheduleUpdater(
    private val htmlParser: Parser<HtmlParseRequest, HtmlParseResponse>,
    private val updatingService: UpdatingService,
    private val phaseService: PhaseService,
    private val dailyScheduleService: DailyScheduleService,
    private val phaseDescriptionService: PhaseDescriptionService,
    private val phaseActionService: PhaseActionService,
    private val namedQueryService: NamedQueryService
) {
    val scheduleGroupDescriptionNameNamedQuery = "SCHEDULE_GROUP_DESCRIPTION"
    val sendGroupDescriptionNameNamedQuery = "SEND_GROUP_DESCRIPTION"
    val scheduleGroupDescriptionQueryNamedQuery = """
        WITH phase_name AS (
            SELECT p.name
            FROM phases p
            WHERE p.id = :phase_id
        )
        
        SELECT concat('*', dsh.date, ' ', dsh.week_day, '*', dsh.lessons_data)
        FROM daily_schedules dsh
        WHERE dsh.group_name = (SELECT * FROM phase_name) and dsh.date = current_date;
    """

    val sendGroupDescriptionQueryNamedQuery = """
        WITH phase_name AS (
            SELECT p.name
            FROM phases p
            WHERE p.id = :phase_id
        )
        
        SELECT string_agg(concat('*', date, ' ', week_day, '*',  lessons_data), E'\n\n') as lessons_data
        FROM (
                 SELECT *
                 FROM daily_schedules dsh
                 WHERE dsh.group_name = (SELECT * FROM phase_name)
                 ORDER BY dsh.date
                 LIMIT 10
        ) AS ordered_group_daily_schedules;
    """

    val subscribeNameNamedQuery = "SUBSCRIBE"
    val unsubscribeNameNamedQuery = "UNSUBSCRIBE"

    val subscribeQueryNamedQuery = """
        INSERT INTO phases_users_subscription (phase_id, user_id)
            VALUES (cast(:phase_id AS UUID), cast(:user_id AS UUID));
    """
    val unsubscribeQueryNamedQuery = """
        DELETE FROM phases_users_subscription
            WHERE cast(phase_id AS VARCHAR) = :phase_id AND cast(user_id AS VARCHAR) = :user_id;
    """

    val subscribeConditionNameNamedQuery = "SUBSCRIBE_CONDITION"
    val unsubscribeConditionNameNamedQuery = "UNSUBSCRIBE_CONDITION"

    val subscribeConditionQueryNamedQuery = """
        SELECT count(*) = 0
        FROM phases_users_subscription
        WHERE cast(phase_id AS VARCHAR) = :phase_id AND cast(user_id AS VARCHAR) = :user_id
    """
    val unsubscribeConditionQueryNamedQuery = """
        SELECT count(*) > 0
        FROM phases_users_subscription
        WHERE cast(phase_id AS VARCHAR) = :phase_id AND cast(user_id AS VARCHAR) = :user_id
    """

    val formDataInstituteParamName = "arrFilter_pf[institut]"
    val formDataGroupParamName = "arrFilter_pf[ngroup]"
    val scheduleUrl = "https://www.bgsha.com/ru/academy_info/rp/student.php"
    val instituteCssSelector = "select#institut option:not([selected])"
    val groupCssSelector = "select#ngroup option:not([selected])"
    val scheduleForDayFirstWeekCssSelector = "div.maincontent div#print-1 > div.row"
    val scheduleForDaySecondWeekCssSelector = "div.maincontent div#print-2 > div.row"
    val datePairsCssSelector = "div.row div.sell-date > p"
    val pairCssSelector = "div.border"
    val timePairCssSelector = "div.border > div:first-child > p"
    val pairDataCssSelector = "div.border > div.sell > div"

    @Scheduled(cron = "\${schedule.parser.html}")
    fun updateSchedule() {

        val updatingList = updatingService.findByType(UpdatingType.SCHEDULE)

        updatingList.forEach { updating ->
            val schedulePhase = updating.parentPhase

            val parsedInstitutes = htmlParser.parse(
                HtmlParseRequest(
                    scheduleUrl,
                    method = HttpMethod.GET,
                    cssSelectors = setOf(instituteCssSelector)
                )
            ).let { parsedResponse ->
                parsedResponse.data[instituteCssSelector]
                    ?.let { parsedItems -> parsedItems.map { it.text() } }
                    ?: emptyList()
            }

            val existInstituteNames = schedulePhase.childNamesWithShortIds.map { (name, _) -> name }
            schedulePhase.childNamesWithShortIds.filter { (instituteName, _) ->
                parsedInstitutes.notContains(instituteName)
            }.forEach { (_, oldInstitutePhaseShortId) ->
                phaseService.findByShortId(oldInstitutePhaseShortId, PhaseDescriptionType.SEND)
                    ?.let { oldInstitute ->
                        oldInstitute.childNamesWithShortIds.forEach { (groupName, groupShortId) ->
                            phaseService.delete(groupShortId)
                            dailyScheduleService.delete(groupName)
                        }
                        phaseService.delete(oldInstitutePhaseShortId)
                    }
            }

            parsedInstitutes.filter { instituteName ->
                existInstituteNames.notContains(instituteName)
            }.forEach { newInstituteName ->
                phaseService.create(CreatePhase(newInstituteName, schedulePhase.id))
            }

            val institutePhaseList = phaseService.findByParentId(schedulePhase.id)

            institutePhaseList.forEach { institute ->
                val parsedGroups = htmlParser.parse(
                    HtmlParseRequest(
                        scheduleUrl,
                        method = HttpMethod.POST,
                        cssSelectors = setOf(groupCssSelector),
                        body = BodyInserters.fromFormData(formDataInstituteParamName, institute.name)
                    )
                ).let { parsedResponse ->
                    parsedResponse.data[groupCssSelector]
                        ?.let { parsedItems -> parsedItems.map { it.text() } } ?: emptyList()
                }

                val existGroupNames = institute.childNamesWithShortIds.map { (name, _) -> name }
                institute.childNamesWithShortIds.filter { (groupName, _) ->
                    parsedGroups.notContains(groupName)
                }.forEach { (oldGroupName, oldGroupPhaseShortId) ->
                    phaseService.delete(oldGroupPhaseShortId)
                    dailyScheduleService.delete(oldGroupName)
                }

                parsedGroups.filter { groupName ->
                    existGroupNames.notContains(groupName)
                }.forEach { newGroupName ->
                    phaseService.create(CreatePhase(newGroupName, institute.id))
                        ?.let { phase ->
                            namedQueryService.createIfNotExist(
                                CreateNamedQuery(
                                    scheduleGroupDescriptionNameNamedQuery,
                                    scheduleGroupDescriptionQueryNamedQuery,
                                    NamedQueryType.SELECT,
                                    listOf(
                                        CreateNamedQueryInputParam(
                                            "phase_id",
                                            "ignored",
                                            NamedQueryInputParamType.FROM_ENTITY_FIELDS_CONTEXT
                                        )
                                    )
                                )
                            )?.let { namedQuery ->
                                phaseDescriptionService.createIfNotExist(
                                    CreatePhaseDescription(
                                        phase.id,
                                        PhaseDescriptionType.SCHEDULE_SEND,
                                        namedQuery.id,
                                        PhaseDescriptionValueType.DYNAMIC,
                                        PhaseDescriptionFormatType.MARKDOWN
                                    ),

                                    )
                            }

                            namedQueryService.createIfNotExist(
                                CreateNamedQuery(
                                    sendGroupDescriptionNameNamedQuery,
                                    sendGroupDescriptionQueryNamedQuery,
                                    NamedQueryType.SELECT,
                                    listOf(
                                        CreateNamedQueryInputParam(
                                            "phase_id",
                                            "ignored",
                                            NamedQueryInputParamType.FROM_ENTITY_FIELDS_CONTEXT
                                        )
                                    )
                                )
                            )?.let { namedQuery ->
                                phaseDescriptionService.createIfNotExist(
                                    CreatePhaseDescription(
                                        phase.id,
                                        PhaseDescriptionType.SEND,
                                        namedQuery.id,
                                        PhaseDescriptionValueType.DYNAMIC,
                                        PhaseDescriptionFormatType.MARKDOWN
                                    )
                                )
                            }

                            namedQueryService.createIfNotExist(
                                CreateNamedQuery(
                                    subscribeNameNamedQuery,
                                    subscribeQueryNamedQuery,
                                    NamedQueryType.INSERT,
                                    listOf(
                                        CreateNamedQueryInputParam(
                                            "phase_id",
                                            "ignored",
                                            NamedQueryInputParamType.FROM_ENTITY_FIELDS_CONTEXT
                                        ),
                                        CreateNamedQueryInputParam(
                                            "user_id",
                                            "ignored",
                                            NamedQueryInputParamType.FROM_ENTITY_FIELDS_CONTEXT
                                        )
                                    )
                                )
                            )
                                ?.let { actionNamedQuery ->
                                    namedQueryService.createIfNotExist(
                                        CreateNamedQuery(
                                            subscribeConditionNameNamedQuery,
                                            subscribeConditionQueryNamedQuery,
                                            NamedQueryType.SELECT,
                                            listOf(
                                                CreateNamedQueryInputParam(
                                                    "phase_id",
                                                    "ignored",
                                                    NamedQueryInputParamType.FROM_ENTITY_FIELDS_CONTEXT
                                                ),
                                                CreateNamedQueryInputParam(
                                                    "user_id",
                                                    "ignored",
                                                    NamedQueryInputParamType.FROM_ENTITY_FIELDS_CONTEXT
                                                )
                                            )
                                        )
                                    )?.let { conditionNamedQuery ->
                                        phaseActionService.create(
                                            CreatePhaseAction(
                                                phaseId = phase.id,
                                                name = "Подписаться",
                                                displayConditionNamedQueryId = conditionNamedQuery.id,
                                                actionNamedQueryId = actionNamedQuery.id
                                            )
                                        )
                                    }
                                }

                            namedQueryService.createIfNotExist(
                                CreateNamedQuery(
                                    unsubscribeNameNamedQuery,
                                    unsubscribeQueryNamedQuery,
                                    NamedQueryType.DELETE,
                                    listOf(
                                        CreateNamedQueryInputParam(
                                            "phase_id",
                                            "ignored",
                                            NamedQueryInputParamType.FROM_ENTITY_FIELDS_CONTEXT
                                        ),
                                        CreateNamedQueryInputParam(
                                            "user_id",
                                            "ignored",
                                            NamedQueryInputParamType.FROM_ENTITY_FIELDS_CONTEXT
                                        )
                                    )
                                )
                            )
                                ?.let { actionNamedQuery ->
                                    namedQueryService.createIfNotExist(
                                        CreateNamedQuery(
                                            unsubscribeConditionNameNamedQuery,
                                            unsubscribeConditionQueryNamedQuery,
                                            NamedQueryType.SELECT,
                                            listOf(
                                                CreateNamedQueryInputParam(
                                                    "phase_id",
                                                    "ignored",
                                                    NamedQueryInputParamType.FROM_ENTITY_FIELDS_CONTEXT
                                                ),
                                                CreateNamedQueryInputParam(
                                                    "user_id",
                                                    "ignored",
                                                    NamedQueryInputParamType.FROM_ENTITY_FIELDS_CONTEXT
                                                )
                                            )
                                        )
                                    )?.let { conditionNamedQuery ->
                                        phaseActionService.create(
                                            CreatePhaseAction(
                                                phaseId = phase.id,
                                                name = "Отписаться",
                                                displayConditionNamedQueryId = conditionNamedQuery.id,
                                                actionNamedQueryId = actionNamedQuery.id
                                            )
                                        )
                                    }
                                }
                        }
                }
                val groupPhaseList = phaseService.findByParentId(institute.id)

                groupPhaseList.forEach { group ->
                    val now = LocalDate.now()
                    if (now.dayOfWeek == DayOfWeek.MONDAY) {
                        dailyScheduleService.delete(group.name, now.minusWeeks(1))
                    }

                    val createDailyScheduleList = htmlParser.parse(
                        HtmlParseRequest(
                            scheduleUrl,
                            method = HttpMethod.POST,
                            cssSelectors = setOf(
                                scheduleForDayFirstWeekCssSelector,
                                scheduleForDaySecondWeekCssSelector
                            ),
                            body = BodyInserters.fromFormData(
                                LinkedMultiValueMap(
                                    mutableMapOf(
                                        formDataInstituteParamName to mutableListOf(institute.name),
                                        formDataGroupParamName to mutableListOf(group.name),
                                    )
                                ),
                            )
                        )
                    ).let { parsedResponse ->
                        val firstWeekSchedules = parsedResponse.data[scheduleForDayFirstWeekCssSelector]
                            ?: emptyList()
                        val secondWeekSchedules = parsedResponse.data[scheduleForDaySecondWeekCssSelector]
                            ?: emptyList()

                        firstWeekSchedules.plus(secondWeekSchedules)
                            .map {
                                val dateList = it.select(datePairsCssSelector).text().split(" ")
                                val weekDay = dateList[0]
                                val date = LocalDate.parse(dateList[1], DateTimeFormatter.ofPattern("dd.MM.yyyy"))
                                val lessonsData = it.select(pairCssSelector)
                                    .map { pairNode ->

                                        val timePair = pairNode.select(timePairCssSelector)
                                            .first()?.text() ?: ""
                                        val pairData = pairNode.select(pairDataCssSelector)
                                            .joinToString(
                                                prefix = "\n        ",
                                                separator = "\n        "
                                            ) { "`${it.text()}`" }

                                        "    $timePair$pairData"
                                    }
                                    .joinToString(prefix = "\n", separator = "\n")

                                CreateDailySchedule(weekDay, date, lessonsData, group.name)
                            }
                    }

                    createDailyScheduleList.forEach(dailyScheduleService::createIfNotExist)
                }
            }
        }
    }
}