package com.softwaret.kpdf.routing.routes

import com.softwaret.kpdf.controller.publication.PublicationsController
import com.softwaret.kpdf.model.inline.Description
import com.softwaret.kpdf.model.inline.Id
import com.softwaret.kpdf.model.inline.PdfBase64
import com.softwaret.kpdf.model.inline.PublicationName
import com.softwaret.kpdf.util.extension.respondWith
import com.softwaret.kpdf.util.extension.toForm
import com.softwaret.kpdf.util.extension.userLoginFromPrincipal
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.locations.*
import io.ktor.routing.*
import kotlinx.coroutines.Dispatchers

@KtorExperimentalLocationsAPI
fun Routing.publications(controller: PublicationsController) {

    @Location("/publications")
    data class GetPublication(val publicationId: Int)

    @Location("/publications")
    class PostPublication

    @Location("/publications")
    data class PutPublication(val publicationId: Int, val pdfBase64: String)

    @Location("/publications")
    data class DeletePublication(val publicationId: Int)

    authenticate {
        get<GetPublication> { getPublicationModel ->
            call.respondWith(controller.obtainPublication(Id(getPublicationModel.publicationId)))
        }

        post<PostPublication> {
            call.respondWith(
                call.toForm(Dispatchers.IO) { form ->
                    controller.insertPublication(
                        form.get { PublicationName(it) },
                        form.get { PdfBase64(it) },
                        call.userLoginFromPrincipal,
                        form.get { Description(it) }
                    )
                }
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
