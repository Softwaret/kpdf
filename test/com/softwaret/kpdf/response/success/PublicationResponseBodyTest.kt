package com.softwaret.kpdf.response.success

import com.softwaret.kpdf.model.inline.Description
import com.softwaret.kpdf.model.inline.Id
import com.softwaret.kpdf.model.inline.Login
import com.softwaret.kpdf.model.inline.PdfFile
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class PublicationResponseBodyTest {

    @Test
    fun `PublicationResponseBody should be creatable`() {
        val id = Id(1)
        val name = "name"
        val authorLogin = Login("authorLogin")
        val pdf = PdfFile("pdf".toByteArray())
        val description = Description("description")

        val body = PublicationResponseBody(id, name, authorLogin, description)
        assertEquals(id, body.id)
        assertEquals(name, body.name)
        assertEquals(authorLogin, body.authorLogin)
    }
}
