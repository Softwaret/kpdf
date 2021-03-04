package com.softwaret.kpdf.db.tables.pdf

import com.softwaret.kpdf.model.inline.PdfBase64
import java.io.InputStream

data class PdfTile(
    val inputStream: InputStream
) {

    val pdfBase64: PdfBase64
        get() = PdfBase64(inputStream.readBytes().toString())
}
