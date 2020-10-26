package com.softwaret.kpdf.repository

import com.softwaret.kpdf.repository.hash.HashRepository
import com.softwaret.kpdf.repository.hash.SaltingHashRepository
import com.softwaret.kpdf.util.parameters.SALT
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

fun DI.MainBuilder.bindRepositories() {

    bind<HashRepository>() with singleton { SaltingHashRepository(instance(tag = SALT)) }
}
