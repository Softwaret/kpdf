package com.softwaret.kpdf.repository.hash

import java.security.MessageDigest

class SaltingHashRepository(
    private val salt: String
) : HashRepository {

    override fun hashString(raw: String): String {
        val digest = MessageDigest.getInstance("SHA-256").digest("$salt$raw".toByteArray())
        return digest.fold("", { str, it -> str + "%02x".format(it) })
    }
}
