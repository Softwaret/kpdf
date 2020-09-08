package com.softwaret.kpdf.controller.publication

import com.softwaret.kpdf.db.tables.pdf.PdfTile
import com.softwaret.kpdf.db.tables.publication.PublicationTile
import com.softwaret.kpdf.db.tables.user.UserTile
import com.softwaret.kpdf.extension.anyValue
import com.softwaret.kpdf.interactor.publication.PublicationsInteractor
import com.softwaret.kpdf.model.inline.Id
import com.softwaret.kpdf.model.inline.Login
import com.softwaret.kpdf.model.inline.Name
import com.softwaret.kpdf.model.inline.Password
import com.softwaret.kpdf.response.Response
import com.softwaret.kpdf.response.success.PublicationResponseBody
import io.ktor.http.HttpStatusCode
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@ExtendWith(MockKExtension::class)
internal class PublicationsControllerImplTest {

    @RelaxedMockK
    lateinit var interactor: PublicationsInteractor

    lateinit var controller: PublicationsController

    private val testTile
        get() = PublicationTile(
            name = "name",
            author = UserTile(Login("login"), Password("Password"), Name("Name")),
            pdf = PdfTile("asd".byteInputStream())
        )

    @BeforeEach
    fun setup() {
        controller = PublicationsControllerImpl(interactor)
    }

    @Test
    fun `obtainPublication should return BadRequest on missing publication`() {
        val id = Id(1)
        every { interactor.obtainPublicationOrNull(id) } returns null

        val result = controller.obtainPublication(id)

        verify(exactly = 1) { interactor.obtainPublicationOrNull(id) }
        assertTrue { result.code == HttpStatusCode.BadRequest }
    }

    @Test
    fun `obtainPublication should return OK with publication on existing publication`() {
        val id = Id(1)
        val publication = testTile
        every { interactor.obtainPublicationOrNull(id) } returns publication

        val result = controller.obtainPublication(id)

        verify(exactly = 1) { interactor.obtainPublicationOrNull(id) }
        assertTrue { (result.body as PublicationResponseBody).id == id }
        assertResponseEqualsTile(result, publication)
    }

    @Test
    fun `insertPublication should return OK on correct data`() {
        val publication = testTile
        every { interactor.obtainPublicationOrNull(anyValue()) } returns publication

        val result = controller.insertPublication(publication.name, publication.pdf.pdfBase64, publication.author.login)

        assertResponseEqualsTile(result, publication)
    }

    @Test
    fun `insertPublication should return BadRequest on insert fail`() {
        val publication = testTile
        every { interactor.obtainPublicationOrNull(anyValue()) } returns null

        val result = controller.insertPublication(publication.name, publication.pdf.pdfBase64, publication.author.login)

        assertEquals(HttpStatusCode.BadRequest, result.code)
    }

    private fun assertResponseEqualsTile(result: Response, publication: PublicationTile) {
        assertTrue { result.code == HttpStatusCode.OK }
        assertTrue { result.body is PublicationResponseBody }
        assertTrue { (result.body as PublicationResponseBody).authorLogin == publication.author.login }
        assertTrue { (result.body as PublicationResponseBody).name == publication.name }
        assertTrue { (result.body as PublicationResponseBody).pdf.value == publication.pdf.pdfBase64.value }
    }
}
