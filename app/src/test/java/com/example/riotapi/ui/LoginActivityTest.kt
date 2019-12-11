package com.example.riotapi.ui

import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class LoginActivityTest {
    @Test
    fun validateLogin() {
        val email = ""
        val password = ""
        val result = LoginActivity().getValidationError(email, password)
        assertNotNull(result)
    }

    @Test
    fun validateNoEmail() {
        val email = ""
        val password = "test12345"
        val result = LoginActivity().getValidationError(email, password)
        assertNotNull(result)
    }

    @Test
    fun validateNoPassword() {
        val email = "test1@email.com"
        val password = ""
        val result = LoginActivity().getValidationError(email, password)
        assertNotNull(result)
    }

    @Test
    fun validateSuccessfulLogin() {
        val email = "test1@email.com"
        val password = "test12345"
        val result = LoginActivity().getValidationError(email, password)
        assertNull(result)
    }

}