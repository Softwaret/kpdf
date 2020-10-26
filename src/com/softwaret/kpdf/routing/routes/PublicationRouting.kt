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
    data class GetPublication(val publicationId: Int)

    @Location("/publications")
    data class PostPublication(val name: String, val pdfBase64: String)

    @Location("/publications")
    data class PutPublication(val publicationId: Int, val pdfBase64: String)

    @Location("/publications")
    data class DeletePublication(val publicationId: Int)

    authenticate {
        get<GetPublication> { getPublicationModel ->
            call.respondWith(controller.obtainPublication(Id(getPublicationModel.publicationId)))
        }

        post<PostPublication> { postPublicationLocation ->
            call.respondWith(
                controller.insertPublication(
                    postPublicationLocation.name,
                    PdfBase64(postPublicationLocation.pdfBase64),
                    call.userLoginFromPrincipal
                )
            )
        }

        put<PutPublication> { putPublicationLocation ->
            call.respondWith(
                controller.updatePublication(
                    Id(putPublicationLocation.publicationId),
                    PdfBase64(putPublicationLocation.pdfBase64)
                )
            )
        }

        delete<DeletePublication> { deletePublicationLocation ->
            call.respondWith(
                controller.deletePublication(Id(deletePublicationLocation.publicationId))
            )
        }
    }
}
