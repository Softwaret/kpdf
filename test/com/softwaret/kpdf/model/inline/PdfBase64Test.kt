package com.softwaret.kpdf.model.inline

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class PdfBase64Test {

    @Test
    fun `pdfBase64 should keep string value`() {
        val raw = "asd"
        val pdf = PdfBase64(raw)

        assertEquals(raw, pdf.value)
    }
}
