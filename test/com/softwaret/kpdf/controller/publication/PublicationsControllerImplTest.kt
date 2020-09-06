package com.softwaret.kpdf.controller.publication

import com.softwaret.kpdf.db.tables.pdf.PdfTile
import com.softwaret.kpdf.db.tables.publication.PublicationTile
import com.softwaret.kpdf.db.tables.user.UserTile
import com.softwaret.kpdf.interactor.publication.PublicationsInteractor
import com.softwaret.kpdf.model.inline.Id
import com.softwaret.kpdf.model.inline.Login
import com.softwaret.kpdf.model.inline.Name
import com.softwaret.kpdf.model.inline.Password
import com.softwaret.kpdf.response.success.PublicationResponseBody
import io.ktor.http.HttpStatusCode
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import kotlin.test.assertTrue

@ExtendWith(MockKExtension::class)
class PublicationsControllerImplTest {

    @MockK
    lateinit var interactor: PublicationsInteractor

    lateinit var controller: PublicationsController

    @BeforeEach
    fun setupMocks() {
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
        val publication = PublicationTile(
            name = "name",
            author = UserTile(Login("login"), Password("Password"), Name("Name")),
            pdf = PdfTile("asd".byteInputStream())
        )
        every { interactor.obtainPublicationOrNull(id) } returns publication

        val result = controller.obtainPublication(id)

        verify(exactly = 1) { interactor.obtainPublicationOrNull(id) }
        assertTrue { result.code == HttpStatusCode.OK }
        assertTrue { result.body is PublicationResponseBody }
        assertTrue { (result.body as PublicationResponseBody).id == id }
        assertTrue { (result.body as PublicationResponseBody).authorLogin == publication.author.login }
        assertTrue { (result.body as PublicationResponseBody).name == publication.name }
        assertTrue { (result.body as PublicationResponseBody).pdf.value == publication.pdf.pdfBase64.value }
    }
}