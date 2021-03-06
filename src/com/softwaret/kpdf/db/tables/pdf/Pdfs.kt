package com.softwaret.kpdf.db.tables.pdf

import org.jetbrains.exposed.dao.id.IntIdTable

object Pdfs : IntIdTable() {

    private const val PDF_FIELD_NAME = "pdf"

    val blobPdf = blob(PDF_FIELD_NAME)
}
