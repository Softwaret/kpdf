package com.softwaret.kpdf.util.extension

import com.softwaret.kpdf.response.Response
import com.softwaret.kpdf.response.file.FileResponse
import com.softwaret.kpdf.util.log.Log
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.auth.jwt.*
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.utils.io.core.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import kotlin.coroutines.CoroutineContext

val ApplicationCall.userLoginFromPrincipal
    get() = run {
        val login = principal<JWTPrincipal>()?.payload?.userLogin
        checkNotNull(login) { "User authentication required" }
    }

suspend fun ApplicationCall.respondWithFile(
    fileResponse: FileResponse,
    fileDir: File,
    context: CoroutineContext = Dispatchers.IO
) = when (fileResponse.code) {
    HttpStatusCode.NotFound -> respond(fileResponse.code)
    else -> respondFile(fileDir, fileResponse.fileName, fileResponse.file, context)
}

private suspend fun ApplicationCall.respondFile(
    fileDir: File,
    fileName: String,
    fileBytes: ByteArray,
    context: CoroutineContext = Dispatchers.IO
) {
    try {
        withContext(context) {
            val file = File(fileDir, fileName)
            val fileOutStream = FileOutputStream(file)
            fileOutStream.write(fileBytes)
            fileOutStream.close()
            respondFile(fileDir, fileName)
            file.delete()
        }
    } catch (e: IOException) {
        Log.e(e)
        respond(HttpStatusCode.InternalServerError)
    }
}

internal suspend fun ApplicationCall.respondWith(response: Response) =
    respond(status = response.code, message = response.body)

internal class Form(
    private val formItems: HashMap<String, String>,
    private val fileItems: HashMap<String, ByteArray>,
    private val binaryItems: HashMap<String, ByteArray>
) {

    inline fun <reified T> getTextItem(create: (String) -> T) =
        (formItems[T::class.simpleName?.toKey()] ?: "").let(create)

    inline fun <reified T> getFileItem(create: (ByteArray) -> T) =
        (fileItems[T::class.simpleName?.toKey()] ?: ByteArray(0)).let(create)

    inline fun <reified T> getBinaryItem(create: (ByteArray) -> T) =
        (binaryItems[T::class.simpleName?.toKey()] ?: ByteArray(0)).let(create)
}

internal suspend fun ApplicationCall.receiveForm(context: CoroutineContext, data: (Form) -> Response) =
    withContext(context) {
        val formItems: HashMap<String, String> = hashMapOf()
        val fileItems: HashMap<String, ByteArray> = hashMapOf()
        val binaryItems: HashMap<String, ByteArray> = hashMapOf()
        receiveMultipart().forEachPart { part ->
            when (part) {
                is PartData.FileItem -> {
                    part.name?.toKey()?.let { name ->
                        try {
                            fileItems[name] = part.streamProvider().trySafeReadingOrEmpty()
                        } catch (exception: IOException) {
                            respond(HttpStatusCode.InternalServerError)
                        }
                    }
                }
                is PartData.FormItem -> {
                    part.name?.toKey()?.let { name -> formItems[name] = part.value }
                }
                is PartData.BinaryItem -> {
                    part.name?.toKey()?.let { name ->
                        part.provider().apply {
                            binaryItems[name] = readBytes()
                        }.close()
                    }
                }
            }
            part.dispose()
        }
        data(Form(formItems, fileItems, binaryItems))
    }

private fun String.toKey() = toLowerCase()
