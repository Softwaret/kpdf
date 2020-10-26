package com.softwaret.kpdf.response.success

import com.softwaret.kpdf.model.inline.Id
import com.softwaret.kpdf.model.inline.Login
import com.softwaret.kpdf.model.inline.PdfBase64
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class PublicationResponseBodyTest {

    @Test
    fun `PublicationResponseBody should be creatable`() {
        val id = Id(1)
        val name = "name"
        val authorLogin = Login("authorLogin")
        val pdf = PdfBase64("pdf")

        val body = PublicationResponseBody(id, name, authorLogin, pdf)
        assertEquals(id, body.id)
        assertEquals(name, body.name)
        assertEquals(authorLogin, body.authorLogin)
        assertEquals(pdf, body.pdf)
    }
}
