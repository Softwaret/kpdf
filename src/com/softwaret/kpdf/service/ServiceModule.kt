package com.softwaret.kpdf.service

import com.softwaret.kpdf.service.token.JwtTokenService
import com.softwaret.kpdf.service.token.TokenService
import com.softwaret.kpdf.service.user.UserService
import com.softwaret.kpdf.service.user.UserServiceImpl
import com.softwaret.kpdf.service.validation.input.InputValidator
import com.softwaret.kpdf.service.validation.input.InputValidatorImpl
import com.softwaret.kpdf.util.parameters.JwtParameters
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

fun DI.MainBuilder.bindServices(jwtParameters: JwtParameters) {

    bind<UserService>() with singleton { UserServiceImpl(instance()) }

    bind<TokenService>() with singleton {
        JwtTokenService(
            jwtParameters.algorithm,
            jwtParameters.validityPeriod,
            jwtParameters.issuer
        )
    }

    bind<InputValidator>() with singleton { InputValidatorImpl(instance(), instance(), instance()) }
}
