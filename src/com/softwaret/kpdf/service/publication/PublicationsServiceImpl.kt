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
import org.jetbrains.exposed.exceptions.ExposedSQLException
import org.jetbrains.exposed.sql.statements.api.ExposedBlob
import org.jetbrains.exposed.sql.transactions.transaction

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
        pdfFile: PdfFile,
        login: Login,
        description: Description
    ) = try {
        transaction {
            Publication.new {
                name = publicationName.value
                author = User.find { Users.login eq login.value }.first()
                pdf = Pdf.new { blobPdf = ExposedBlob(pdfFile.bytes) }
                metadata = Metadata.new { this.description = description.value }
            }
        }.id.value.asId()
    } catch (e: ExposedSQLException) {
        throw PublicationException.PublicationNotCreated
    }

    override fun deletePublication(id: Id) {
        transaction {
            Publication.findById(id.value)?.delete() ?: throw PublicationException.PublicationNotFound
        }
    }

    override fun updatePublication(id: Id, pdfFile: PdfFile) {
        transaction {
            Publication.findById(id.value)?.let {
                it.pdf.blobPdf = ExposedBlob(pdfFile.bytes)
            } ?: throw PublicationException.PublicationNotFound
        }
    }
}
