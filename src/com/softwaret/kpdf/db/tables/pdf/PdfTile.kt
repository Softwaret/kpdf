package com.softwaret.kpdf.db.tables.pdf

import com.softwaret.kpdf.model.inline.PdfFile
import com.softwaret.kpdf.util.extension.trySafeReadingOrEmpty
import java.io.InputStream

data class PdfTile(
    private val inputStream: InputStream
) {

    private val bytes by lazy { inputStream.trySafeReadingOrEmpty() }

    val pdfFile: PdfFile
        get() = PdfFile(bytes)
}
