package com.softwaret.kpdf.repository.hash

interface HashRepository {

    fun hashString(raw: String): String
}
