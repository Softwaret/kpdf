package com.softwaret.kpdf.util.exception

sealed class PublicationException(message: String?) : Throwable(message) {

    object PublicationNotFound : PublicationException("Publication has not been found")

    object PublicationNotCreated : PublicationException("Publication has not been created")
}
