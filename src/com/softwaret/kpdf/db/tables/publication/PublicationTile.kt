package com.softwaret.kpdf.db.tables.publication

import com.softwaret.kpdf.db.tables.pdf.Pdf
import com.softwaret.kpdf.db.tables.user.User

data class PublicationTile(
    var name: String,
    var author: User,
    val pdf: Pdf
)