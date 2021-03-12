package com.softwaret.kpdf.controller.publication

import com.softwaret.kpdf.interactor.publication.PublicationsInteractor
import com.softwaret.kpdf.model.inline.*
import com.softwaret.kpdf.response.*
import com.softwaret.kpdf.response.error.ErrorResponseBody
import com.softwaret.kpdf.response.file.FileResponse
import com.softwaret.kpdf.response.file.emptyNotFound
import com.softwaret.kpdf.response.file.ok
import com.softwaret.kpdf.response.success.EmptyResponseBody
import com.softwaret.kpdf.response.success.PublicationCreatedResponseBody
import com.softwaret.kpdf.response.success.PublicationResponseBody
import com.softwaret.kpdf.util.exception.PublicationException

class PublicationsControllerImpl(
    private val interactor: PublicationsInteractor
) : PublicationsController {

    override fun obtainPublication(id: Id) =
        interactor.obtainPublicationOrNull(id)?.let {
            Response.ok(
                PublicationResponseBody(
                    id,
                    it.name,
                    it.author.login,
                    it.metadata.description
                )
            )
        } ?: Response.notFound(EmptyResponseBody)

    override fun insertPublication(
        publicationName: PublicationName,
        pdfFile: PdfFile,
        login: Login,
        description: Description
    ): Response {
        if (interactor.obtainPublicationOrNull(login, publicationName) != null) {
            return Response.conflict(ErrorResponseBody.ResourceAlreadyExists)
        }
        return try {
            val id = interactor.insertPublication(publicationName, pdfFile, login, description)
            Response.created(
                PublicationCreatedResponseBody(
                    id
                )
            )
        } catch (exception: PublicationException) {
            handlePublicationException(exception)
        }
    }

    override fun updatePublication(id: Id, pdfFile: PdfFile) =
        onPublicationExists(id) { interactor.updatePublication(id, pdfFile) }

    private fun onPublicationExists(
        id: Id,
        publicationExistsResponse: Response = Response.ok(EmptyResponseBody),
        publicationDoesNotExistResponse: Response = Response.notFound(EmptyResponseBody),
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
            PublicationException.PublicationNotFound -> Response.notFound(EmptyResponseBody)
            PublicationException.PublicationNotCreated -> Response.internalServerError(EmptyResponseBody)
        }

    override fun deletePublication(id: Id) =
        onPublicationExists(id) { interactor.deletePublication(id) }

    override fun getPublicationFile(id: Id): FileResponse {
        val pub = interactor.obtainPublicationOrNull(id)
        return if (pub == null) {
            FileResponse.emptyNotFound()
        } else {
            FileResponse.ok(pub.name.pdfExtension(), pub.pdf.pdfFile.bytes)
        }
    }

    private fun String.pdfExtension() = "$this.pdf"
}
