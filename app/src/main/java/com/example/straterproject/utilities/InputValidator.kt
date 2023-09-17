package com.example.straterproject.utilities


object InputValidator {

    fun checkPasswordValidation(password: String): InputValidationState {

        if (password.length <  8) {
            return InputValidationState.InValid("Minimum 8 Character Password")
        }

        return InputValidationState.Valid
    }
    fun checkEmailValidation(email: String): InputValidationState {
        if (email.matches(EMAIL_REGEX)) {
            return InputValidationState.Valid
        }
        return InputValidationState.InValid("Email is badly formatted")
    }

    fun emailAndPasswordIsValid(email: String , password: String): Boolean {
        return checkEmailValidation(email) is InputValidationState.Valid &&
                checkPasswordValidation(password) is InputValidationState.Valid
    }

    fun checkPhoneNumberValidation(phoneNumber: String) = phoneNumber.length == 11

    fun checkOtpCodeValidation(otpCode: String) = otpCode.length == 6



}