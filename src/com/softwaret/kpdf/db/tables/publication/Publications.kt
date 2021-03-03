package com.softwaret.kpdf.db.tables.publication

import com.softwaret.kpdf.db.tables.metadata.Metadatas
import com.softwaret.kpdf.db.tables.pdf.Pdfs
import com.softwaret.kpdf.db.tables.user.Users
import org.jetbrains.exposed.dao.IntIdTable

object Publications : IntIdTable() {
    private const val NAME_LENGTH = 150

    val name = varchar("name", NAME_LENGTH)
    val author = reference("author", Users)
    val pdf = reference("pdf", Pdfs)
    val metadata = reference("metadata", Metadatas)
}
