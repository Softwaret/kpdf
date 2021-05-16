package com.softwaret.kpdf.db.tables.pdf

import org.junit.jupiter.api.Test
import kotlin.test.assertTrue

internal class PdfTileTest {

    @Test
    fun `pdfTile should return inputStream content`() {
        val raw = "WmFyYXogc2nEmcKgc3DDs8W6bmnEmQ=="
        val rawBytes = raw.toByteArray()
        val tile = PdfTile(raw.byteInputStream())

        assertTrue { rawBytes.contentEquals(tile.pdfFile.bytes) }
    }

    @Test
    fun `pdfTile should return empty inputStream content when called second time`() {
        val raw = "WmFyYXogc2nEmcKgc3DDs8W6bmnEmQ=="
        val rawBytes = raw.toByteArray()
        val tile = PdfTile(raw.byteInputStream())

        assertTrue { rawBytes.contentEquals(tile.pdfFile.bytes) }
        assertTrue { rawBytes.contentEquals(tile.pdfFile.bytes) }
    }
}
