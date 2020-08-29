package com.softwaret.kpdf.db.tables.publication

import com.softwaret.kpdf.db.tables.pdf.Pdf
import com.softwaret.kpdf.db.tables.user.User
import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass

class Publication(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Publication>(Publications)

    var name by Publications.name
    var author by User referencedOn Publications.author
    val pdf by Pdf referencedOn Publications.pdf
}

fun Publication.toPublicationTile() = PublicationTile(
    name = name,
    author = author,
    pdf = pdf
)