package com.softwaret.kpdf.response.error

enum class ErrorType : ErrorData {

    AUTORIZATION_FAILED {
        override val message: String
            get() = "AUTORIZATION FAILED"
    },
    CANNOT_REGISTER_USER {
        override val message: String
            get() = "CANNOT REGISTER USER WITH GIVEN DATA"
    },
    UNKNOWN {
        override val message: String
            get() = "UNKNOWN ERROR OCCURRED"
    }
}