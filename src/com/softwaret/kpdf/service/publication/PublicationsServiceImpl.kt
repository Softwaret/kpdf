package com.softwaret.kpdf.service.publication

import com.softwaret.kpdf.db.tables.pdf.Pdf
import com.softwaret.kpdf.db.tables.publication.Publication
import com.softwaret.kpdf.db.tables.publication.toPublicationTile
import com.softwaret.kpdf.db.tables.user.User
import com.softwaret.kpdf.db.tables.user.Users
import com.softwaret.kpdf.model.inline.Id
import com.softwaret.kpdf.model.inline.Login
import com.softwaret.kpdf.model.inline.PdfBase64
import com.softwaret.kpdf.util.exception.PublicationException
import com.softwaret.kpdf.util.extension.asId
import org.jetbrains.exposed.sql.transactions.transaction
import javax.sql.rowset.serial.SerialBlob

class PublicationsServiceImpl : PublicationsService {

    override fun obtainPublicationOrNull(id: Id) =
        transaction { Publication.findById(id.value)?.toPublicationTile() }

    override fun insertPublication(publicationName: String, pdfBase64: PdfBase64, login: Login) =
        transaction {
            Publication.new {
                name = publicationName
                author = User.find { Users.login eq login.value }.first()
                pdf = Pdf.new { blobPdf = SerialBlob(pdfBase64.value.toByteArray()) }
            }
        }.id.value.asId()

    override fun deletePublication(id: Id) {
        transaction {
            Publication.findById(id.value)?.delete() ?: throw PublicationException.PublicationNotFound
        }
    }

    override fun updatePublication(id: Id, pdfBase64: PdfBase64) {
        transaction {
            Publication.findById(id.value)?.let {
                it.pdf.blobPdf = SerialBlob(pdfBase64.value.toByteArray())
            } ?: throw PublicationException.PublicationNotFound
        }
    }
}
