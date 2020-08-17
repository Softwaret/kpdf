package com.softwaret.kpdf.util.extension

fun isAnyNotNull(vararg any: Any?) = any.any { it != null }

fun areAllNull(vararg any: Any?) = any.all { it == null }
