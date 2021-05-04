package com.softwaret.kpdf.controller.publication

import com.softwaret.kpdf.model.inline.*
import com.softwaret.kpdf.response.Response
import com.softwaret.kpdf.response.file.FileResponse

interface PublicationsController {

    fun obtainPublication(id: Id): Response

    fun insertPublication(
        publicationName: PublicationName,
        pdfFile: PdfFile,
        login: Login,
        description: Description
    ): Response

    fun updatePublication(id: Id, pdfFile: PdfFile): Response

    fun deletePublication(id: Id): Response

    fun getPublicationFile(id: Id): FileResponse
}
