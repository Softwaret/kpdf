package com.softwaret.kpdf.controller.publications

import com.softwaret.kpdf.model.inline.Id
import com.softwaret.kpdf.model.inline.PdfBase64
import com.softwaret.kpdf.response.Response

interface PublicationsController  {

    fun getPublication(id: Id): Response
    fun savePublication(pdfBase64: PdfBase64): Response
}