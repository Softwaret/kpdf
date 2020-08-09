package com.softwaret.kpdf.util.extension

import kotlin.contracts.contract

inline fun requireNotNullAndNotBlank(value: String?, lazyMessage: () -> String) {
    contract {
        returns() implies (value != null)
    }

    require(value.isNullOrBlank().not(), lazyMessage)
    return
}
