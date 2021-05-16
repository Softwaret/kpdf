package com.softwaret.kpdf.interactor.publication

import com.softwaret.kpdf.model.inline.*
import com.softwaret.kpdf.service.publication.PublicationsService

class PublicationsInteractorImpl(
    private val publicationsService: PublicationsService
) : PublicationsInteractor {

    override fun obtainPublicationOrNull(id: Id) =
        publicationsService.obtainPublicationOrNull(id)

    override fun obtainPublicationOrNull(
        login: Login,
        publicationName: PublicationName
    ) = publicationsService.obtainPublicationOrNull(login, publicationName)

    override fun insertPublication(
        publicationName: PublicationName,
        pdfFile: PdfFile,
        login: Login,
        description: Description
    ) = publicationsService.insertPublication(
        publicationName,
        pdfFile,
        login,
        description
    )

    override fun deletePublication(id: Id) =
        publicationsService.deletePublication(id)

    override fun updatePublication(id: Id, pdfFile: PdfFile) =
        publicationsService.updatePublication(id, pdfFile)
}
