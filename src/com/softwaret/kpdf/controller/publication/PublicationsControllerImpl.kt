package com.softwaret.kpdf.controller.publication

import com.softwaret.kpdf.interactor.publication.PublicationsInteractor
import com.softwaret.kpdf.model.inline.*
import com.softwaret.kpdf.response.*
import com.softwaret.kpdf.response.error.ErrorResponseBody
import com.softwaret.kpdf.response.success.EmptyResponseBody
import com.softwaret.kpdf.response.success.PublicationResponseBody
import com.softwaret.kpdf.util.exception.PublicationException

class PublicationsControllerImpl(
    private val interactor: PublicationsInteractor
) : PublicationsController {

    override fun obtainPublication(id: Id) =
        interactor.obtainPublicationOrNull(id)?.let {
            Response.OK(
                PublicationResponseBody(
                    id,
                    it.name,
                    it.author.login,
                    it.pdf.pdfBase64,
                    it.metadata.description
                )
            )
        } ?: Response.BadRequest(EmptyResponseBody)

    override fun insertPublication(
        publicationName: PublicationName,
        pdfBase64: PdfBase64,
        login: Login,
        description: Description
    ): Response {
        if (interactor.obtainPublicationOrNull(login, publicationName) != null) {
            return Response.Conflict(ErrorResponseBody.ResourceAlreadyExists)
        }
        val id = interactor.insertPublication(publicationName, pdfBase64, login, description)
        return interactor.obtainPublicationOrNull(id)?.run {
            Response.Created(
                PublicationResponseBody(
                    id,
                    name,
                    author.login,
                    pdf.pdfBase64,
                    metadata.description
                )
            )
        } ?: Response.InternalServerError(ErrorResponseBody.InternalServer)
    }

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
