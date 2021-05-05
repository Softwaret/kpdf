package com.softwaret.kpdf.db.tables.pdf

import com.softwaret.kpdf.base.test.BaseTableTest
import com.softwaret.kpdf.util.extension.readString
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.statements.api.ExposedBlob
import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.jupiter.api.Test
import java.io.ByteArrayInputStream
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class PdfTest : BaseTableTest() {

    override fun createTables() {
        SchemaUtils.create(Pdfs)
    }

    @Test
    fun `create pdf should create new pdf`() {
        val testPdf = "QmxvcmdhIEdsb3JiYQ=="

        transaction {
            val newPdf = Pdf.new {
                blobPdf = ExposedBlob(testPdf.toByteArray())
            }

            assertNotNull(Pdf.findById(newPdf.id))
        }
    }

    @Test
    fun `create pdf should save pdfBas64 string`() {
        val testPdf = "QmxvcmdhIEdsb3JiYQ=="

        transaction {
            val newPdf = Pdf.new {
                blobPdf = ExposedBlob(testPdf.toByteArray())
            }

            assertEquals(
                testPdf,
                Pdf.findById(newPdf.id)?.blobPdf?.bytes?.let { ByteArrayInputStream(it) }?.readString()
            )
        }
    }
}
