package com.softwaret.kpdf.repository.hash

import com.softwaret.kpdf.util.extension.requireNotNullAndNotBlank
import java.security.MessageDigest

class SaltingHashRepository(
    private val salt: String
) : HashRepository {

    override fun hashString(raw: String): String {
        requireNotNullAndNotBlank(raw) { "String to be hashed should not be blank" }

        val digest = MessageDigest.getInstance("SHA-256").digest("$salt$raw".toByteArray())
        return digest.fold("", { str, it -> str + "%02x".format(it) })
    }
}
