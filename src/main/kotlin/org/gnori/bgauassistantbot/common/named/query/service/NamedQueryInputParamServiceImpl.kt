package org.gnori.bgauassistantbot.common.named.query.service

import org.gnori.bgauassistantbot.common.named.query.entity.NamedQueryInputParamEntity
import org.gnori.bgauassistantbot.common.named.query.model.CreateNamedQueryInputParam
import org.gnori.bgauassistantbot.common.named.query.model.NamedQueryInputParam
import org.gnori.bgauassistantbot.common.named.query.model.NamedQueryInputParamImpl
import org.gnori.bgauassistantbot.common.named.query.repository.NamedQueryInputParamEntityRepository
import org.springframework.stereotype.Component
import java.util.*

@Component
class NamedQueryInputParamServiceImpl(
    private val repository: NamedQueryInputParamEntityRepository,
    private val typeService: NamedQueryInputParamTypeService
) : NamedQueryInputParamService {

    override fun findBy(queryId: String): List<NamedQueryInputParam> =
        repository.findByNamedQueryId(UUID.fromString(queryId))
            .mapNotNull(this::findComponentsAndMap)

    override fun createIfNotExist(
        queryId: String,
        createNamedQueryInputParam: CreateNamedQueryInputParam
    ): NamedQueryInputParam? {

        val entity = repository.findByNamedQueryIdAndName(UUID.fromString(queryId), createNamedQueryInputParam.name)
            ?: createInputParam(queryId, createNamedQueryInputParam)

        return entity?.let(this::findComponentsAndMap)
    }

    private fun createInputParam(
        queryId: String,
        createNamedQueryInputParam: CreateNamedQueryInputParam
    ): NamedQueryInputParamEntity? =
        typeService.getId(createNamedQueryInputParam.type)
            ?.let { typeId ->
                repository.saveAndFlush(
                    NamedQueryInputParamEntity(
                        namedQueryId = UUID.fromString(queryId),
                        typeId = UUID.fromString(typeId),
                        name = createNamedQueryInputParam.name,
                        value = createNamedQueryInputParam.value,
                    )
                )
            }

    private fun findComponentsAndMap(inputParam: NamedQueryInputParamEntity): NamedQueryInputParam? =
        typeService.findBy("${inputParam.typeId}")
            ?.let { paramType ->
                NamedQueryInputParamImpl("${inputParam.id}", paramType, inputParam.name, inputParam.value)
            }
}