package com.softwaret.kpdf.util.extension

import com.softwaret.kpdf.util.log.Log
import io.ktor.application.*
import io.ktor.util.*
import java.io.File
import java.io.IOException

private const val MAIN_PROPERTY = "kpdf"

private const val DIR_PROPERTY = "respondFile.dir"

@KtorExperimentalAPI
fun ApplicationEnvironment.getRespondFileDir(): File {
    val responseFileDir = File(config.config(MAIN_PROPERTY).stringProperty(DIR_PROPERTY))
    try {
        if (responseFileDir.mkdirs() && !responseFileDir.exists()) {
            throw IOException("Failed to create directory ${responseFileDir.absolutePath}")
        }
    } catch (exception: IOException) {
        Log.e(exception)
    }
    return responseFileDir
}
