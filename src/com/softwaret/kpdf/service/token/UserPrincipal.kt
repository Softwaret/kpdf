package com.softwaret.kpdf.service.token

import io.ktor.auth.Principal


data class UserPrincipal(val login: String) : Principal


