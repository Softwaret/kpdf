package com.softwaret.kpdf.routing.routes

import com.softwaret.kpdf.controller.publication.PublicationsController
import com.softwaret.kpdf.model.inline.Id
import com.softwaret.kpdf.model.inline.PdfBase64
import com.softwaret.kpdf.util.extension.userLoginFromPrincipal
import io.ktor.application.call
import io.ktor.auth.authenticate
import io.ktor.locations.KtorExperimentalLocationsAPI
import io.ktor.locations.Location
import io.ktor.locations.get
import io.ktor.locations.post
import io.ktor.response.respond
import io.ktor.routing.Routing

@KtorExperimentalLocationsAPI
fun Routing.publications(controller: PublicationsController) {

    @Location("/publications")
    data class GetPublicationLocation(val publicationId: Int)

    @Location("/publications")
    data class PostPublicationLocation(val name: String, val pdfBase64: String)

    authenticate {
        get<GetPublicationLocation> { getPublicationModel ->
            call.respond(controller.obtainPublication(Id(getPublicationModel.publicationId)))
        }

        post<PostPublicationLocation> { postPublicationLocation ->
            call.respond(
                controller.insertPublication(
                    postPublicationLocation.name,
                    PdfBase64(postPublicationLocation.pdfBase64),
                    call.userLoginFromPrincipal
                )
            )
        }
    }
}
