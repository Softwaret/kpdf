package com.softwaret.kpdf.util.extension

import com.softwaret.kpdf.util.log.Log
import java.io.IOException
import java.io.InputStream

fun InputStream.readString() = bufferedReader().use { it.readText() }

fun InputStream.trySafeReadingOrEmpty(): ByteArray =
    try {
        val allBytes = readAllBytes()
        close()
        allBytes
    } catch (exception: IOException) {
        Log.d {
            "Reading stream error"
        }
        Log.e(exception)
        ByteArray(0)
    }
