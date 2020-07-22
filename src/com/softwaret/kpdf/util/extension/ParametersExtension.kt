package com.softwaret.kpdf.util.extension

import com.softwaret.kpdf.util.BodyParameter
import io.ktor.http.Parameters

operator fun Parameters.get(bodyParameter: BodyParameter) = get(bodyParameter.parameterName)

operator fun Parameters.get(vararg bodyParameters: BodyParameter) =
    bodyParameters.map { param -> get(param) }