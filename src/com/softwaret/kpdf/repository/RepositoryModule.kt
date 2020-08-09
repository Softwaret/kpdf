package com.softwaret.kpdf.repository

import com.softwaret.kpdf.repository.hash.HashRepository
import com.softwaret.kpdf.repository.hash.SaltingHashRepository
import com.softwaret.kpdf.repository.validation.input.login.LoginValidationRepository
import com.softwaret.kpdf.repository.validation.input.login.LoginValidationRepositoryImpl
import com.softwaret.kpdf.repository.validation.input.name.NameValidationRepository
import com.softwaret.kpdf.repository.validation.input.name.NameValidationRepositoryImpl
import com.softwaret.kpdf.repository.validation.input.password.PasswordValidationRepository
import com.softwaret.kpdf.repository.validation.input.password.PasswordValidationRepositoryImpl
import com.softwaret.kpdf.util.parameters.ParametersNames.SALT
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

fun DI.MainBuilder.bindPreferences() {

    bind<LoginValidationRepository>() with singleton { LoginValidationRepositoryImpl() }

    bind<NameValidationRepository>() with singleton { NameValidationRepositoryImpl() }

    bind<PasswordValidationRepository>() with singleton { PasswordValidationRepositoryImpl() }

    bind<HashRepository>() with singleton { SaltingHashRepository(instance(tag = SALT)) }
}
