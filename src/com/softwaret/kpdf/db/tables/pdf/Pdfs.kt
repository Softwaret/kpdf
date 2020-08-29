package com.softwaret.kpdf.db.tables.pdf

import org.jetbrains.exposed.dao.IntIdTable

object Pdfs : IntIdTable() {
    private const val PDF_FIELD_NAME = "pdf"

    val pdf = blob(PDF_FIELD_NAME)
}