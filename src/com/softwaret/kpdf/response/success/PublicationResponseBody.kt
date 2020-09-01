package com.softwaret.kpdf.response.success

import com.softwaret.kpdf.model.inline.Id
import com.softwaret.kpdf.model.inline.Login
import com.softwaret.kpdf.model.inline.PdfBase64
import com.softwaret.kpdf.response.ResponseBody

data class PublicationResponseBody(
    val id: Id,
    var name: String,
    var authorLogin: Login,
    val pdf: PdfBase64
) : ResponseBody
