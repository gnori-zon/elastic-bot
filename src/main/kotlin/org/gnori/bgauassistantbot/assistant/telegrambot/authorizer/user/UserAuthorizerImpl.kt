package org.gnori.bgauassistantbot.assistant.telegrambot.authorizer.user

import org.gnori.bgauassistantbot.assistant.telegrambot.authorizer.user.mapper.UserAuthorizeParamToCreateUserParamMapper
import org.gnori.bgauassistantbot.assistant.telegrambot.authorizer.user.model.UserAuthorizeParam
import org.gnori.bgauassistantbot.common.telegrambot.authorizer.user.UserAuthorizer
import org.gnori.bgauassistantbot.common.user.model.User
import org.gnori.bgauassistantbot.common.user.service.UserService
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.switchIfEmpty

@Component
class UserAuthorizerImpl(
    private val userService: UserService,
    private val userAuthorizeParamToCreateUserMapper: UserAuthorizeParamToCreateUserParamMapper
) : UserAuthorizer {

    override fun authorize(param: UserAuthorizeParam): Mono<User> =
        userService.findByChatId(param.chatId)
            .switchIfEmpty {
                Mono.defer {
                    userAuthorizeParamToCreateUserMapper.map(param)
                        .let { userService.create(it) }
                }
            }
}