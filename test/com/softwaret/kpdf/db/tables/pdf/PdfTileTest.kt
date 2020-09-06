package com.softwaret.kpdf.db.tables.pdf

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class PdfTileTest {

    @Test
    fun `pdfBase64 should return inputStream content`() {
        val raw = "WmFyYXogc2nEmcKgc3DDs8W6bmnEmQ=="
        val tile = PdfTile(raw.byteInputStream())

        assertEquals(raw, tile.pdfBase64.value)
    }

    @Test
    fun `pdfBase64 should return inputStream content when called twice`() {
        val raw = "WmFyYXogc2nEmcKgc3DDs8W6bmnEmQ=="
        val tile = PdfTile(raw.byteInputStream())

        assertEquals(raw, tile.pdfBase64.value)
        assertEquals(raw, tile.pdfBase64.value)
    }
}
