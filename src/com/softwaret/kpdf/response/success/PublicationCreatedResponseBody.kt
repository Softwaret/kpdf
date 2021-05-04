package com.softwaret.kpdf.response.success

import com.softwaret.kpdf.model.inline.Id
import com.softwaret.kpdf.response.ResponseBody

data class PublicationCreatedResponseBody(
    val id: Id
) : ResponseBody
