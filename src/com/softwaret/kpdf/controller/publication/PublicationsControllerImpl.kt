package com.softwaret.kpdf.controller.publication

import com.softwaret.kpdf.interactor.publication.PublicationsInteractor
import com.softwaret.kpdf.model.inline.Id
import com.softwaret.kpdf.model.inline.Login
import com.softwaret.kpdf.model.inline.PdfBase64
import com.softwaret.kpdf.response.BadRequest
import com.softwaret.kpdf.response.OK
import com.softwaret.kpdf.response.Response
import com.softwaret.kpdf.response.success.EmptyResponseBody
import com.softwaret.kpdf.response.success.PublicationResponseBody
import com.softwaret.kpdf.util.exception.PublicationException

class PublicationsControllerImpl(
    private val interactor: PublicationsInteractor
) : PublicationsController {

    override fun obtainPublication(id: Id) =
        interactor.obtainPublicationOrNull(id)?.let {
            Response.OK(PublicationResponseBody(id, it.name, it.author.login, it.pdf.pdfBase64))
        } ?: Response.BadRequest(EmptyResponseBody)

    override fun insertPublication(publicationName: String, pdfBase64: PdfBase64, login: Login) =
        obtainPublication(id = interactor.insertPublication(publicationName, pdfBase64, login))

    override fun deletePublication(id: Id) =
        onPublicationExists(id) { interactor.deletePublication(id) }

    override fun updatePublication(id: Id, pdfBase64: PdfBase64) =
        onPublicationExists(id) { interactor.updatePublication(id, pdfBase64) }

    private fun onPublicationExists(
        id: Id,
        publicationExistsResponse: Response = Response.OK(EmptyResponseBody),
        publicationDoesNotExistResponse: Response = Response.BadRequest(EmptyResponseBody),
        publicationFoundAction: () -> Unit
    ) = if (interactor.obtainPublicationOrNull(id) == null) {
        publicationDoesNotExistResponse
    } else {
        try {
            publicationFoundAction()
            publicationExistsResponse
        } catch (ex: PublicationException) {
            handlePublicationException(ex)
        }
    }

    private fun handlePublicationException(ex: PublicationException) =
        when (ex) {
            PublicationException.PublicationNotFound -> Response.BadRequest(EmptyResponseBody)
        }
}
