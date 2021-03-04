package com.softwaret.kpdf.controller.register

import com.softwaret.kpdf.extension.anyValue
import com.softwaret.kpdf.interactor.register.RegisterInteractor
import com.softwaret.kpdf.model.inline.Login
import com.softwaret.kpdf.model.inline.Name
import com.softwaret.kpdf.model.inline.Password
import com.softwaret.kpdf.validation.InputValidator
import com.softwaret.kpdf.validation.bindValidators
import io.ktor.http.*
import io.mockk.clearMocks
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.kodein.di.DI
import kotlin.test.assertEquals

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RegisterControllerImplTest {

    private data class TestRegisterParams(
        val login: Login = Login("login"),
        val password: Password = Password("password"),
        val name: Name = Name("name")
    )

    private val interactor: RegisterInteractor = mockk()

    private val di = DI {
        bindValidators()
    }

    private val inputValidator = InputValidator(di)

    private lateinit var controller: RegisterController

    @BeforeEach
    fun setup() {
        clearMocks(interactor)
        controller = RegisterControllerImpl(interactor, inputValidator)
    }

    @Nested
    inner class BadRequestOnUserParam {

        private fun setupInteractorForInvalidUserData() {
            every { interactor.registerUser(anyValue(), anyValue(), anyValue()) }
            every { interactor.doesUserExists(anyValue()) } returns false
        }

        @BeforeEach
        fun setup() {
            setupInteractorForInvalidUserData()
        }

        private fun registrationResult(testRegisterParams: TestRegisterParams) =
            testRegisterParams.run {
                controller.register(
                    login,
                    password,
                    name
                )
            }

        @Test
        fun `register should return bad request on invalid login`() {
            val badRegisterParams = TestRegisterParams(login = Login(""))
            val result = registrationResult(badRegisterParams)
            verify(exactly = 0) { interactor.registerUser(anyValue(), anyValue(), anyValue()) }
            assertEquals(HttpStatusCode.BadRequest, result.code)
        }

        @Test
        fun `register should return bar request on invalid password`() {
            val badRegisterParams = TestRegisterParams(name = Name(""))
            val result = registrationResult(badRegisterParams)
            verify(exactly = 0) { interactor.registerUser(anyValue(), anyValue(), anyValue()) }
            assertEquals(HttpStatusCode.BadRequest, result.code)
        }

        @Test
        fun `register should return bad requet on invalid name`() {
            val badRegisterParams = TestRegisterParams(password = Password("          "))
            val result = registrationResult(badRegisterParams)
            verify(exactly = 0) { interactor.registerUser(anyValue(), anyValue(), anyValue()) }
            assertEquals(HttpStatusCode.BadRequest, result.code)
        }
    }

    @Test
    fun `register with password login should not trigger register process`() {
        val badRegisterParams = TestRegisterParams(password = Password(""))
        every { interactor.registerUser(anyValue(), anyValue(), anyValue()) }
        controller.register(
            badRegisterParams.login,
            badRegisterParams.password,
            badRegisterParams.name
        )
        verify(exactly = 0) { interactor.registerUser(anyValue(), anyValue(), anyValue()) }
    }

    @Test
    fun `register with existing user should result in bad request`() {
        val testRegisterParams = TestRegisterParams()
        every { interactor.doesUserExists(testRegisterParams.login) } returns true
        val result = testRegisterParams.run {
            controller.register(
                login,
                password,
                name
            )
        }
        assertEquals(HttpStatusCode.BadRequest, result.code)
    }

    @Test
    fun `register with exisiting user should result in login already taken error response body`() {
        val testRegisterParams = TestRegisterParams()
        every { interactor.doesUserExists(testRegisterParams.login) } returns true
        val result = testRegisterParams.run {
            controller.register(
                login,
                password,
                name
            )
        }
        assertEquals(
            RegisterErrorResponseBody.LoginAlreadyTaken,
            result.body as RegisterErrorResponseBody.LoginAlreadyTaken
        )
    }

    private fun setupInteractorForProperRegistration() {
        every { interactor.doesUserExists(anyValue()) } returns false
        every {
            interactor.registerUser(
                anyValue(),
                anyValue(),
                anyValue()
            )
        } returns Unit
    }

    @Test
    fun `register with proper user data should result in created`() {
        val testRegisterParams = TestRegisterParams()
        setupInteractorForProperRegistration()
        val result = testRegisterParams.run {
            controller.register(
                login,
                password,
                name
            )
        }
        assertEquals(HttpStatusCode.Created, result.code)
    }

    @Test
    fun `register with proper user data should result in triggering registration process`() {
        val testRegisterParams = TestRegisterParams()
        setupInteractorForProperRegistration()
        testRegisterParams.run {
            controller.register(
                login,
                password,
                name
            )
        }
        verify(exactly = 1) { interactor.registerUser(anyValue(), anyValue(), anyValue()) }
    }
}