package com.softwaret.kpdf.util.extension

import io.ktor.http.Parameters

operator fun Parameters.get(bodyParameter: BodyParameter) = get(bodyParameter.parameterName)

operator fun Parameters.get(vararg bodyParameters: BodyParameter) =
    bodyParameters.map { param -> get(param) }
