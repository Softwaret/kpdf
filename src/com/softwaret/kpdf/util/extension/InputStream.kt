package com.softwaret.kpdf.util.extension

import java.io.InputStream

fun InputStream.readString() = bufferedReader().use { it.readText() }
