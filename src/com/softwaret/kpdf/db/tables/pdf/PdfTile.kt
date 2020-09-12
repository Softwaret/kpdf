package com.softwaret.kpdf.db.tables.pdf

import com.softwaret.kpdf.model.inline.PdfBase64
import com.softwaret.kpdf.util.extension.readString
import java.io.InputStream

data class PdfTile(
    val inputStream: InputStream
) {

    val pdfBase64: PdfBase64
        get() = PdfBase64(inputStream.readString()).also { inputStream.reset() }
}
