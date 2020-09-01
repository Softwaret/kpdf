package com.softwaret.kpdf.controller.publication

import com.softwaret.kpdf.model.inline.Id
import com.softwaret.kpdf.model.inline.Login
import com.softwaret.kpdf.model.inline.PdfBase64
import com.softwaret.kpdf.response.Response

interface PublicationsController {

    fun obtainPublication(id: Id): Response
    fun insertPublication(publicationName: String, pdfBase64: PdfBase64, login: Login): Response
}
