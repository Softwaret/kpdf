package com.softwaret.kpdf.service.token

interface TokenService {

    fun generateToken(claim: String): String
}
