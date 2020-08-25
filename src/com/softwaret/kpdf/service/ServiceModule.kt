package com.softwaret.kpdf.service

import com.softwaret.kpdf.service.token.JWTTokenVeryfingService
import com.softwaret.kpdf.service.token.JWTTokenVeryfingServiceImpl
import com.softwaret.kpdf.service.token.JwtTokenService
import com.softwaret.kpdf.service.token.TokenService
import com.softwaret.kpdf.service.user.UserService
import com.softwaret.kpdf.service.user.UserServiceImpl
import com.softwaret.kpdf.service.validation.input.InputValidator
import com.softwaret.kpdf.service.validation.input.InputValidatorImpl
import com.softwaret.kpdf.util.parameters.ServiceParameters
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

fun DI.MainBuilder.bindServices(serviceParameters: ServiceParameters) {

    bind<UserService>() with singleton { UserServiceImpl(instance()) }

    bind<TokenService>() with singleton {
        with(serviceParameters) {
            JwtTokenService(
                jwtParameters.algorithm,
                jwtParameters.validityPeriod,
                jwtParameters.issuer
            )
        }
    }

    bind<JWTTokenVeryfingService>() with singleton {
        JWTTokenVeryfingServiceImpl()
    }

    bind<InputValidator>() with singleton { InputValidatorImpl(instance(), instance(), instance()) }
}
