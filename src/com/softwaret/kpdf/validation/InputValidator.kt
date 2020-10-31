package com.softwaret.kpdf.validation

import com.softwaret.kpdf.validation.result.ValidationResult
import com.softwaret.kpdf.validation.validators.Validator
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.direct
import org.kodein.di.instance

class InputValidator(
    override val di: DI
) : DIAware {

    @Suppress("UNCHECKED_CAST")
    inline fun <reified T> validate(param: T) =
        (di.direct.instance<Validator<*>>(tag = T::class.java) as? Validator<T>)?.validate(param)
            ?: ValidationResult.GenericError

    inline fun <reified T : Any> T.isValid() =
        validate(this@isValid).let { result ->
            if (result is ValidationResult.Result) {
                result.results.isEmpty()
            } else {
                result == ValidationResult.Valid
            }
        }
}
