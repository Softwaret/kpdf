package com.softwaret.kpdf.util.extension

import com.softwaret.kpdf.response.Response
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.auth.jwt.*
import io.ktor.http.content.*
import io.ktor.request.*
import io.ktor.response.*
import kotlinx.coroutines.withContext
import java.util.*
import kotlin.coroutines.CoroutineContext

val ApplicationCall.userLoginFromPrincipal
    get() = run {
        val login = principal<JWTPrincipal>()?.payload?.userLogin
        checkNotNull(login) { "User authentication required" }
    }

suspend fun ApplicationCall.respondWith(response: Response) =
    respond(status = response.code, message = response.body)

internal class Form(private val values: HashMap<String, String>) {

    inline fun <reified T> get(create: (String) -> T) =
        (values[T::class.simpleName?.toLowerCase()] ?: "").let(create)
}

internal suspend fun ApplicationCall.toForm(context: CoroutineContext, data: (Form) -> Response) =
    withContext(context) {
        val values = hashMapOf<String, String>()
        receiveMultipart().forEachPart { part ->
            when (part) {
                is PartData.FileItem -> {
                    part.name?.let { name ->
                        values[name] = Base64.getEncoder().encodeToString(part.streamProvider().readBytes())
                    }
                }
                is PartData.FormItem -> {
                    part.name?.let { name -> values[name] = part.value }
                }
            }
            part.dispose()
        }
        data(Form(values))
    }
