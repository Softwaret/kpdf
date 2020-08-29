package com.softwaret.kpdf.routing.routes

import com.softwaret.kpdf.controller.publications.PublicationsController
import com.softwaret.kpdf.model.inline.Id
import com.softwaret.kpdf.model.inline.PdfBase64
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
    data class GetPublicationLocation(val publicationId: String)

    @Location("/publications")
    data class PostPublicationLocation(val pdfBase64: String)


    authenticate {
        get<GetPublicationLocation> { getPublicationModel ->
            call.respond(controller.getPublication(Id(getPublicationModel.publicationId)))
        }

        post<PostPublicationLocation> { postPublicationLocation ->
            call.respond(controller.savePublication(PdfBase64(postPublicationLocation.pdfBase64)))
        }
    }
}