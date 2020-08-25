package com.softwaret.kpdf.repository.hash

interface HashRepository {

    fun hash(raw: String): String
}
