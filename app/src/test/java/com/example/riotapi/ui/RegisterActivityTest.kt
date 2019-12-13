package com.example.riotapi.ui

import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Test

class RegisterActivityTest {
    @Test
    fun validateNoEmailNoPassword() {
        val email = ""
        val password = ""
        val result = RegisterActivity().getRegisterValidationError(email, password)
        assertNotNull(result)
    }

    //Test if there is no email but a password
    @Test
    fun validateNoEmail() {
        val email = ""
        val password = "test12345"
        val result = RegisterActivity().getRegisterValidationError(email, password)
        assertNotNull(result)
    }

    //Test if there is an email but no password
    @Test
    fun validateNoPassword() {
        val email = "test1@email.com"
        val password = ""
        val result = RegisterActivity().getRegisterValidationError(email, password)
        assertNotNull(result)
    }

    //Test if there is both an emal and a password
    @Test
    fun validateEmailAndPassword() {
        val email = "test1@email.com"
        val password = "test12345"
        val result = RegisterActivity().getRegisterValidationError(email, password)
        assertNull(result)
    }
}