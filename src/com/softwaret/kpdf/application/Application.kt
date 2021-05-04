@file:Suppress("EXPERIMENTAL_API_USAGE")

package com.softwaret.kpdf.application

import com.fasterxml.jackson.databind.SerializationFeature
import com.softwaret.kpdf.controller.bindControllers
import com.softwaret.kpdf.db.H2Db
import com.softwaret.kpdf.interactor.bindInteractors
import com.softwaret.kpdf.repository.bindRepositories
import com.softwaret.kpdf.routing.routes.login
import com.softwaret.kpdf.routing.routes.publications
import com.softwaret.kpdf.routing.routes.register
import com.softwaret.kpdf.service.bindServices
import com.softwaret.kpdf.service.token.JWTTokenVeryfingService
import com.softwaret.kpdf.service.user.UserService
import com.softwaret.kpdf.util.RESPOND_FILE_DIR
import com.softwaret.kpdf.util.bindUtils
import com.softwaret.kpdf.util.extension.*
import com.softwaret.kpdf.util.parameters.JwtParameters
import com.softwaret.kpdf.util.parameters.ServiceParameters
import com.softwaret.kpdf.util.parameters.bindParameters
import com.softwaret.kpdf.validation.bindValidators
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.auth.jwt.*
import io.ktor.features.*
import io.ktor.jackson.*
import io.ktor.locations.*
import io.ktor.routing.*
import org.kodein.di.ktor.di
import java.io.File

private const val APP_CONF_PATH = "resources/application.conf"
private const val CONFIG_ARG_NAME = "-config="

private val Application.userService
    get() = instance<UserService>()

private val Application.jwtTokenVerifierService
    get() = instance<JWTTokenVeryfingService>()

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(addConfFileLocation(args))

private fun addConfFileLocation(args: Array<String>) = when {
    args.any { it.contains(CONFIG_ARG_NAME) } -> args
    File(APP_CONF_PATH).exists() -> args + "$CONFIG_ARG_NAME$APP_CONF_PATH"
    else -> args
}

@Suppress("unused")
fun Application.main() {
    bindDI()
    installFeatures()
    setupDb()
    bindRouting()
}

private fun Application.installFeatures() {
    install(Locations)
    install(ContentNegotiation) {
        jackson {
            configure(SerializationFeature.INDENT_OUTPUT, true)
        }
    }
    install(DefaultHeaders)
    install(PartialContent)
    install(CallLogging)

    install(Authentication) {
        jwt {
            realm = environment.config.realm
            verifier(buildJwtVerifier())
            validate { validateCredential(it) }
        }
    }
}

private fun Application.validateCredential(jwtCredential: JWTCredential) =
    if (userService.doesUserExist(jwtCredential.loginFromPayload)) {
        JWTPrincipal(jwtCredential.payload)
    } else {
        null
    }

private fun setupDb() {
    H2Db.init()
}

private fun Application.bindDI() {
    di {
        bindParameters(
            salt = environment.config.stringProperty("config.SALT")
        )
        bindUtils(environment.getRespondFileDir())
        bindValidators()
        bindControllers()
        bindInteractors()
        bindServices(obtainParameters())
        bindRepositories()
    }
}

private fun Application.bindRouting() {
    routing {
        login(instance())
        register(instance())
        publications(instance(), instance(tag = RESPOND_FILE_DIR))
    }
}

private fun Application.obtainParameters() = environment.config.run {
    ServiceParameters(
        jwtParameters = JwtParameters(
            algorithm,
            validity,
            issuer
        )
    )
}

private fun Application.buildJwtVerifier() = environment.config.run {
    jwtTokenVerifierService.buildVerifier(algorithm, issuer)
}
