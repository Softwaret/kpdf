package com.softwaret.kpdf.db.tables.metadata

import org.jetbrains.exposed.dao.IntIdTable

object Metadatas : IntIdTable() {
    private const val DESCRIPTION_LENGTH = 500

    val description = varchar("description", DESCRIPTION_LENGTH)
}