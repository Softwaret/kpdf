package com.softwaret.kpdf.util.log

import mu.KLogger
import mu.KotlinLogging

object Log {

    private const val loggerName = "KPDF"

    private val logger: KLogger by lazy { KotlinLogging.logger(loggerName) }

    fun d(message: () -> String) =
        logger.takeIf { it.isDebugEnabled }?.debug(message)
}
