package com.softwaret.kpdf.db.tables.metadata

import com.softwaret.kpdf.model.inline.Description
import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass

class Metadata(id: EntityID<Int>) : IntEntity(id) {

    companion object : IntEntityClass<Metadata>(Metadatas)

    var description by Metadatas.description
}

fun Metadata.toMetadataTile() =
    MetadataTile(
        Description(description)
    )

