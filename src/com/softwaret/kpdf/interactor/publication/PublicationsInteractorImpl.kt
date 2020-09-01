package com.softwaret.kpdf.interactor.publication

import com.softwaret.kpdf.model.inline.Id
import com.softwaret.kpdf.model.inline.Login
import com.softwaret.kpdf.model.inline.PdfBase64
import com.softwaret.kpdf.service.publication.PublicationsService

class PublicationsInteractorImpl(
    private val publicationsService: PublicationsService
) : PublicationsInteractor {
    override fun obtainPublicationOrNull(id: Id) =
        publicationsService.obtainPublicationOrNull(id)

    override fun insertPublication(publicationName: String, pdfBase64: PdfBase64, login: Login) =
        publicationsService.insertPublication(publicationName, pdfBase64, login)
}
