package com.softwaret.kpdf.service.publication

import com.softwaret.kpdf.db.tables.publication.PublicationTile
import com.softwaret.kpdf.model.inline.Id
import com.softwaret.kpdf.model.inline.Login
import com.softwaret.kpdf.model.inline.PdfBase64

interface PublicationsService {

    fun obtainPublicationOrNull(id: Id): PublicationTile?

    fun insertPublication(publicationName: String, pdfBase64: PdfBase64, login: Login): Id

    fun deletePublication(id: Id)

    fun updatePublication(id: Id, pdfBase64: PdfBase64)
}
