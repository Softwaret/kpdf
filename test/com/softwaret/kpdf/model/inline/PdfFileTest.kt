package com.softwaret.kpdf.model.inline

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class PdfFileTest {

    @Test
    fun `pdfFile should keep byteArray value`() {
        val raw = "asd".toByteArray()
        val pdf = PdfFile(raw)

        assertEquals(raw, pdf.bytes)
    }
}
