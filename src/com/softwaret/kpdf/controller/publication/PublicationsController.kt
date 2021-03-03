package com.softwaret.kpdf.controller.publication

import com.softwaret.kpdf.model.inline.*
import com.softwaret.kpdf.response.Response

interface PublicationsController {

    fun obtainPublication(id: Id): Response

    fun insertPublication(
        publicationName: PublicationName,
        pdfBase64: PdfBase64,
        login: Login,
        description: Description
    ): Response

    fun updatePublication(id: Id, pdfBase64: PdfBase64): Response

    fun deletePublication(id: Id): Response
}
