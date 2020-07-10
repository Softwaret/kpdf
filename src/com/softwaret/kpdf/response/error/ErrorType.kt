package com.softwaret.kpdf.response.error

enum class ErrorType : ErrorData {

    NULL_EMPTY_LOGIN {
        override val message: String
            get() = "LOGIN CANNOT BE NULL OR EMPTY"
    },
    NULL_EMPTY_PASSWORD {
        override val message: String
            get() = "PASSWORD CANNOT BE NULL OR EMPTY"
    },
    USER_NOT_EXISTS {
        override val message: String
            get() = "USER DOES NOT EXIST"
    },
    INVALID_CREDENTIALS {
        override val message: String
            get() = "GIVEN CREDENTIALS ARE INVALID"
    },
    UNKNOWN {
        override val message: String
            get() = "UNKNOWN ERROR OCCURRED"
    }
}