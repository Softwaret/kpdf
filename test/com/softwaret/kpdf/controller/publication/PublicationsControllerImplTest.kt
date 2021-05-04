package com.softwaret.kpdf.controller.publication

import com.softwaret.kpdf.db.tables.metadata.MetadataTile
import com.softwaret.kpdf.db.tables.pdf.PdfTile
import com.softwaret.kpdf.db.tables.publication.PublicationTile
import com.softwaret.kpdf.db.tables.user.UserTile
import com.softwaret.kpdf.extension.anyValue
import com.softwaret.kpdf.interactor.publication.PublicationsInteractor
import com.softwaret.kpdf.model.inline.*
import com.softwaret.kpdf.response.Response
import com.softwaret.kpdf.response.success.PublicationCreatedResponseBody
import com.softwaret.kpdf.response.success.PublicationResponseBody
import com.softwaret.kpdf.util.exception.PublicationException
import io.ktor.http.*
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
            pdf = PdfTile("asd".byteInputStream()),
            metadata = MetadataTile(Description("description"))
        )

    @BeforeEach
    fun setup() {
        controller = PublicationsControllerImpl(interactor)
    }

    @Test
    fun `obtainPublication should return NotFound on missing publication`() {
        val id = Id(1)
        every { interactor.obtainPublicationOrNull(id) } returns null

        val result = controller.obtainPublication(id)

        verify(exactly = 1) { interactor.obtainPublicationOrNull(id) }
        assertTrue { result.code == HttpStatusCode.NotFound }
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
    fun `insertPublication should return Created on correct data`() {
        val publication = testTile
        every { interactor.obtainPublicationOrNull(anyValue(), anyValue()) } returns null
        every { interactor.obtainPublicationOrNull(anyValue()) } returns publication

        val result = controller.insertPublication(
            PublicationName(publication.name),
            publication.pdf.pdfFile,
            publication.author.login,
            Description(publication.metadata.description.value)
        )

        assertEquals(result.code, HttpStatusCode.Created)
        assertTrue { result.body is PublicationCreatedResponseBody }
    }

    @Test
    fun `insertPublication should return InternalServerError code on sql insert failure`() {
        val publication = testTile
        every { interactor.obtainPublicationOrNull(anyValue(), anyValue()) } returns null
        every {
            interactor.insertPublication(anyValue(), anyValue(), anyValue(), anyValue())
        } throws PublicationException.PublicationNotCreated

        val result = controller.insertPublication(
            PublicationName(publication.name),
            publication.pdf.pdfFile,
            publication.author.login,
            Description(publication.metadata.description.value)
        )

        assertEquals(HttpStatusCode.InternalServerError, result.code)
    }

    @Test
    fun `insertPublication should return Conflict on insert fail`() {
        val publication = testTile
        every { interactor.obtainPublicationOrNull(anyValue(), anyValue()) } returns publication

        val result = controller.insertPublication(
            PublicationName(publication.name),
            publication.pdf.pdfFile,
            publication.author.login,
            Description(publication.metadata.description.value)
        )

        assertEquals(HttpStatusCode.Conflict, result.code)
    }

    private fun assertResponseEqualsTile(
        result: Response,
        publication: PublicationTile,
        expectedCode: HttpStatusCode = HttpStatusCode.OK
    ) {
        val publicationResponseBody = result.body as PublicationResponseBody
        assertEquals(result.code, expectedCode)
        assertTrue { result.body is PublicationResponseBody }
        assertEquals(publicationResponseBody.authorLogin, publication.author.login)
        assertEquals(publicationResponseBody.name, publication.name)
        assertEquals(publicationResponseBody.description.value, publication.metadata.description.value)
    }

    @Test
    fun `deletePublication should return NotFound on missing publication`() {
        every { interactor.obtainPublicationOrNull(anyValue()) } returns null
        val result = controller.deletePublication(Id(1))
        assertEquals(HttpStatusCode.NotFound, result.code)
    }

    @Test
    fun `updatePublication should return NotFound on missing publication`() {
        every { interactor.obtainPublicationOrNull(anyValue()) } returns null
        val result = controller.updatePublication(Id(1), testTile.pdf.pdfFile)
        assertEquals(HttpStatusCode.NotFound, result.code)
    }

    @Test
    fun `deletePublication should return OK on deleted publication`() {
        val id = Id(1)
        every { interactor.obtainPublicationOrNull(anyValue()) } returns testTile

        val result = controller.deletePublication(id)

        verify(exactly = 1) { interactor.deletePublication(id) }
        assertEquals(HttpStatusCode.OK, result.code)
    }

    @Test
    fun `updatePublication should return OK on updated publication`() {
        val id = Id(1)
        every { interactor.obtainPublicationOrNull(anyValue()) } returns testTile

        val result = controller.updatePublication(id, testTile.pdf.pdfFile)

        verify(exactly = 1) { interactor.updatePublication(id, testTile.pdf.pdfFile) }
        assertEquals(HttpStatusCode.OK, result.code)
    }
}
