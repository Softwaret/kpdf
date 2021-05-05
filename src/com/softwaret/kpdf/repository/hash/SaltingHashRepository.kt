package com.softwaret.kpdf.repository.hash

import com.softwaret.kpdf.util.extension.requireNotNullAndNotBlank
import java.security.MessageDigest

class SaltingHashRepository(
    private val salt: String
) : HashRepository {

    companion object {
        private enum class HashType(val raw: String) {
            SHA_512("SHA-512")
        }
    }

    override fun hash(raw: String): String {
        requireNotNullAndNotBlank(raw) { "String to be hashed should not be blank" }
        return hash(raw, HashType.SHA_512)
    }

    private fun hash(raw: String, type: HashType): String {
        val digest = MessageDigest.getInstance(type.raw).digest("$salt$raw".toByteArray())
        return digest.fold("") { hash, chunk -> hash + "%02x".format(chunk) }
    }
}
