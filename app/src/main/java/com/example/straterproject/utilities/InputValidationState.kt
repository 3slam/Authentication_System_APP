package com.example.straterproject.utilities

sealed class InputValidationState {
    object Valid : InputValidationState()
    data class InValid(val message: String) : InputValidationState()
}
