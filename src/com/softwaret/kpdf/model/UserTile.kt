package com.softwaret.kpdf.model

data class UserTile(
    val login: String,
    val password: String,
    val name: String
) {

    companion object {

        fun from(
            login: String,
            password: String,
            name: String
        ) = UserTile(
            login,
            password,
            name
        )
    }
}