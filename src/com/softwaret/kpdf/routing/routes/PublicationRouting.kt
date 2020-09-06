package com.softwaret.kpdf.routing.routes

import com.softwaret.kpdf.controller.publication.PublicationsController
import com.softwaret.kpdf.model.inline.Id
import com.softwaret.kpdf.model.inline.PdfBase64
import com.softwaret.kpdf.util.extension.respondWith
import com.softwaret.kpdf.util.extension.userLoginFromPrincipal
import io.ktor.application.call
import io.ktor.auth.authenticate
import io.ktor.locations.*
import io.ktor.routing.Routing

@KtorExperimentalLocationsAPI
fun Routing.publications(controller: PublicationsController) {

    @Location("/publications")
    data class GetPublicationLocation(val publicationId: Int)

    @Location("/publications")
    data class PostPublicationLocation(val name: String, val pdfBase64: String)

    @Location("/publications")
    data class PutPublicationLocation(val publicationId: Int, val pdfBase64: String)

    @Location("/publications")
    data class DeletePublicationLocation(val publicationId: Int)

    authenticate {
        get<GetPublicationLocation> { getPublicationModel ->
            call.respondWith(controller.obtainPublication(Id(getPublicationModel.publicationId)))
        }

        post<PostPublicationLocation> { postPublicationLocation ->
            call.respondWith(
                controller.insertPublication(
                    postPublicationLocation.name,
                    PdfBase64(postPublicationLocation.pdfBase64),
                    call.userLoginFromPrincipal
                )
            )
        }

        put<PutPublicationLocation> { putPublicationLocation ->
            call.respondWith(
                controller.updatePublication(
                    Id(putPublicationLocation.publicationId),
                    PdfBase64(putPublicationLocation.pdfBase64)
                )
            )
        }

        delete<DeletePublicationLocation> { deletePublicationLocation ->
            call.respondWith(
                controller.deletePublication(Id(deletePublicationLocation.publicationId))
            )
        }
    }
}
