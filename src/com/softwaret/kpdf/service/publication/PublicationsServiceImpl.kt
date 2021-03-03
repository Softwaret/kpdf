package com.softwaret.kpdf.service.publication

import com.softwaret.kpdf.db.tables.metadata.Metadata
import com.softwaret.kpdf.db.tables.pdf.Pdf
import com.softwaret.kpdf.db.tables.publication.Publication
import com.softwaret.kpdf.db.tables.publication.Publications
import com.softwaret.kpdf.db.tables.publication.toPublicationTile
import com.softwaret.kpdf.db.tables.user.User
import com.softwaret.kpdf.db.tables.user.Users
import com.softwaret.kpdf.model.inline.*
import com.softwaret.kpdf.util.exception.PublicationException
import com.softwaret.kpdf.util.extension.asId
import org.jetbrains.exposed.sql.transactions.transaction
import javax.sql.rowset.serial.SerialBlob

class PublicationsServiceImpl : PublicationsService {

    override fun obtainPublicationOrNull(id: Id) =
        transaction { Publication.findById(id.value)?.toPublicationTile() }

    override fun obtainPublicationOrNull(login: Login, publicationName: PublicationName) =
        transaction {
            Publication
                .find { Publications.name eq publicationName.value }
                .firstOrNull { it.author.login == login.value }
                ?.toPublicationTile()
        }

    override fun insertPublication(
        publicationName: PublicationName,
        pdfBase64: PdfBase64,
        login: Login,
        description: Description
    ) =
        transaction {
            Publication.new {
                name = publicationName.value
                author = User.find { Users.login eq login.value }.first()
                pdf = Pdf.new { blobPdf = SerialBlob(pdfBase64.value.toByteArray()) }
                metadata = Metadata.new { this.description = description.value }
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
