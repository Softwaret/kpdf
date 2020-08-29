package com.softwaret.kpdf.db.tables.pdf

import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass

class Pdf(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Pdf>(Pdfs)

    var pdf by Pdfs.pdf
}

fun Pdf.toPdfTile() = PdfTile(
    inputStream = pdf.binaryStream
)