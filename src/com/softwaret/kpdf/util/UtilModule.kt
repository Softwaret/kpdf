package com.softwaret.kpdf.util

import io.ktor.util.*
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.singleton
import java.io.File

const val RESPOND_FILE_DIR = "RESPOND_FILE_DIR"

@KtorExperimentalAPI
fun DI.MainBuilder.bindUtils(respondFileDir: File) {
    bind<File>(tag = RESPOND_FILE_DIR) with singleton { respondFileDir }
}
