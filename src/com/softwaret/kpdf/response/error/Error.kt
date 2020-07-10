package com.softwaret.kpdf.response.error

import java.io.Serializable

data class Error(
    override val name: String,
    override val message: String
) : Serializable, ErrorData

