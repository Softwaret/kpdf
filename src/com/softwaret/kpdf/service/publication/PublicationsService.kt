package com.softwaret.kpdf.service.publication

import com.softwaret.kpdf.db.tables.publication.PublicationTile
import com.softwaret.kpdf.model.inline.*

interface PublicationsService {

    fun obtainPublicationOrNull(id: Id): PublicationTile?

    fun obtainPublicationOrNull(login: Login, publicationName: PublicationName): PublicationTile?

    fun insertPublication(
        publicationName: PublicationName,
        pdfBase64: PdfBase64,
        login: Login,
        description: Description
    ): Id

    fun deletePublication(id: Id)

    fun updatePublication(id: Id, pdfBase64: PdfBase64)
}
