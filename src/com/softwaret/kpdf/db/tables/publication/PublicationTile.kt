package com.softwaret.kpdf.db.tables.publication

import com.softwaret.kpdf.db.tables.metadata.Metadata
import com.softwaret.kpdf.db.tables.metadata.MetadataTile
import com.softwaret.kpdf.db.tables.pdf.PdfTile
import com.softwaret.kpdf.db.tables.user.UserTile

data class PublicationTile(
    var name: String,
    var author: UserTile,
    val pdf: PdfTile,
    val metadata: MetadataTile
)
