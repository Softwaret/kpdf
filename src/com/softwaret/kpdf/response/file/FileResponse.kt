package com.softwaret.kpdf.response.file

import io.ktor.http.*

data class FileResponse(
    val code: HttpStatusCode,
    val fileName: String = "",
    val file: ByteArray = ByteArray(0)
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as FileResponse

        if (code != other.code) return false
        if (fileName != other.fileName) return false
        if (!file.contentEquals(other.file)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = code.hashCode()
        result = 31 * result + fileName.hashCode()
        result = 31 * result + file.contentHashCode()
        return result
    }

    companion object
}

fun FileResponse.Companion.ok(fileName: String, file: ByteArray) =
    FileResponse(HttpStatusCode.OK, fileName, file)

fun FileResponse.Companion.emptyNotFound() =
    FileResponse(HttpStatusCode.NotFound)

fun FileResponse.Companion.error() =
    FileResponse(HttpStatusCode.InternalServerError)
