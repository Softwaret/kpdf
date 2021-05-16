package com.softwaret.kpdf.db.tables.pdf

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import java.io.ByteArrayInputStream

class Pdf(id: EntityID<Int>) : IntEntity(id) {

    companion object : IntEntityClass<Pdf>(Pdfs)

    var blobPdf by Pdfs.blobPdf
}

fun Pdf.toPdfTile() = PdfTile(
    inputStream = ByteArrayInputStream(blobPdf.bytes)
)
