package org.gnori.elasticbot.common.named.query.service

import org.gnori.elasticbot.common.named.query.entity.NamedQueryEntity
import org.gnori.elasticbot.common.named.query.model.*
import org.gnori.elasticbot.common.named.query.repository.NamedQueryEntityRepository
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Component
import java.util.*

@Component
class NamedQueryServiceImpl(
    private val repository: NamedQueryEntityRepository,
    private val inputParamService: NamedQueryInputParamService,
    private val typeService: NamedQueryTypeService,
    private val dataBaseClient: NamedParameterJdbcTemplate
) : NamedQueryService {

    private val emptyParam: String = ""

    override fun findBy(id: String): NamedQuery? =
        repository.findById(UUID.fromString(id)).orElse(null)
            ?.let(::findComponentsAndMap)

    override fun executeQueryBy(id: String, fieldsContext: Map<String, Any>): List<Map<String, Any>> =
        this.findBy(id)?.let { namedQuery -> this.execute(namedQuery, fieldsContext) } ?: emptyList()

    override fun execute(namedQuery: NamedQuery, fieldsContext: Map<String, Any>): List<Map<String, Any>> {
        val queryParams = prepareQueryParams(namedQuery.params, fieldsContext)
        if (namedQuery.type == NamedQueryType.SELECT) {
            return dataBaseClient.queryForList(namedQuery.query, queryParams)
        } else {
            val updateResult = dataBaseClient.update(namedQuery.query, queryParams)
            return listOf(mapOf("result" to updateResult))
        }
    }

    override fun createIfNotExist(createNamedQuery: CreateNamedQuery): NamedQuery? {

        val entity = repository.findByName(createNamedQuery.name)
            ?: createNamedQuery(createNamedQuery)

        entity?.id?.let { namedQueryId ->
            createNamedQuery.inputParams.forEach {
                inputParamService.createIfNotExist("$namedQueryId", it)
            }
        }

        return entity?.let(::findComponentsAndMap)
    }

    private fun createNamedQuery(createNamedQuery: CreateNamedQuery): NamedQueryEntity? =
        typeService.getId(createNamedQuery.type)
            ?.let { typeId ->
                repository.saveAndFlush(
                    NamedQueryEntity(
                        name = createNamedQuery.name,
                        query = createNamedQuery.query,
                        typeId = UUID.fromString(typeId)
                    )
                )
            }

    private fun findComponentsAndMap(entity: NamedQueryEntity): NamedQuery? {

        val inputParams = inputParamService.findBy("${entity.id}")
        val type = typeService.findBy("${entity.typeId}") ?: return null

        return NamedQueryImpl("${entity.id}", entity.name, entity.query, type, inputParams)
    }

    private fun prepareQueryParams(
        rawParams: List<NamedQueryInputParam>,
        fieldsContext: Map<String, Any>
    ): Map<String, Any> =
        rawParams.associateBy (
            { param -> param.name },
            { param ->
                when (param.type) {
                    NamedQueryInputParamType.STATIC -> param.value
                    NamedQueryInputParamType.FROM_ENTITY_FIELDS_CONTEXT -> fieldsContext[param.name] ?: emptyParam
                }
            }
        )
}