package com.softwaret.kpdf.db.tables.publication

import com.softwaret.kpdf.db.tables.metadata.Metadata
import com.softwaret.kpdf.db.tables.metadata.toMetadataTile
import com.softwaret.kpdf.db.tables.pdf.Pdf
import com.softwaret.kpdf.db.tables.pdf.toPdfTile
import com.softwaret.kpdf.db.tables.user.User
import com.softwaret.kpdf.db.tables.user.toUserTile
import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass

class Publication(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Publication>(Publications)

    var name by Publications.name
    var metadata by Metadata referencedOn Publications.metadata
    var author by User referencedOn Publications.author
    var pdf by Pdf referencedOn Publications.pdf
}

fun Publication.toPublicationTile() = PublicationTile(
    name = name,
    author = author.toUserTile(),
    pdf = pdf.toPdfTile(),
    metadata = metadata.toMetadataTile()
)
