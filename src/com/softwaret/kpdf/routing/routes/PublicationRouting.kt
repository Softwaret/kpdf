package com.softwaret.kpdf.routing.routes

import com.softwaret.kpdf.controller.publication.PublicationsController
import com.softwaret.kpdf.model.inline.Description
import com.softwaret.kpdf.model.inline.Id
import com.softwaret.kpdf.model.inline.PdfFile
import com.softwaret.kpdf.model.inline.PublicationName
import com.softwaret.kpdf.util.extension.receiveForm
import com.softwaret.kpdf.util.extension.respondWith
import com.softwaret.kpdf.util.extension.respondWithFile
import com.softwaret.kpdf.util.extension.userLoginFromPrincipal
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.locations.*
import io.ktor.routing.*
import kotlinx.coroutines.Dispatchers
import java.io.File

@KtorExperimentalLocationsAPI
fun Routing.publications(controller: PublicationsController, respondFileDir: File) {

    @Location("/publication/{publicationId}")
    data class GetPublication(val publicationId: Int)

    @Location("/publication")
    class PostPublication

    @Location("/publication/{publicationId}")
    data class DeletePublication(val publicationId: Int)

    @Location("/publication/file/{publicationId}")
    data class PutPublicationFile(val publicationId: Int)

    @Location("/publication/file/{publicationId}")
    data class GetPublicationFile(val publicationId: Int)

    authenticate {
        get<GetPublication> { getPublicationModel ->
            call.respondWith(controller.obtainPublication(Id(getPublicationModel.publicationId)))
        }

        post<PostPublication> {
            call.respondWith(
                call.receiveForm(Dispatchers.IO) { form ->
                    controller.insertPublication(
                        form.getTextItem { PublicationName(it) },
                        form.getFileItem { PdfFile(it) },
                        call.userLoginFromPrincipal,
                        form.getTextItem { Description(it) }
                    )
                }
            )
        }

        delete<DeletePublication> { deletePublicationLocation ->
            call.respondWith(
                controller.deletePublication(Id(deletePublicationLocation.publicationId))
            )
        }

        put<PutPublicationFile> { putPublicationLocation ->
            call.respondWith(
                call.receiveForm(Dispatchers.IO) { form ->
                    controller.updatePublication(
                        Id(putPublicationLocation.publicationId),
                        form.getFileItem { PdfFile(it) }
                    )
                }
            )
        }

        get<GetPublicationFile> { getPublicationFileModel ->
            controller.getPublicationFile(Id(getPublicationFileModel.publicationId))
                .let { response ->
                    call.respondWithFile(response, respondFileDir)
                }
        }
    }
}
