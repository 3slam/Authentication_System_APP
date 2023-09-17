package com.example.straterproject.utilities



import com.google.common.truth.Truth.assertThat
import org.junit.Test

class InputValidatorTest {

    @Test
    fun `lower than 8 Character and not Contain 1 Lower-case returns false` (){
        val result = InputValidator.checkPasswordValidation("1234567") == InputValidationState.Valid // false
        assertThat(result).isFalse()
    }



    @Test
    fun `Minimum 8 Character Password and Must Contain 1 Lower-case returns true` (){
          val result = InputValidator.checkPasswordValidation("1234567E") == InputValidationState.Valid // true
          assertThat(result).isTrue()
    }

    @Test
    fun `Email is good format return true` (){
        val result = InputValidator.checkEmailValidation("eslam@gmail.com") == InputValidationState.Valid // true
        assertThat(result).isTrue()
    }

    @Test
    fun `Email is badly format return false` (){
        val result = InputValidator.checkEmailValidation("eslagmail.com") == InputValidationState.Valid // false
        assertThat(result).isFalse()
    }


}