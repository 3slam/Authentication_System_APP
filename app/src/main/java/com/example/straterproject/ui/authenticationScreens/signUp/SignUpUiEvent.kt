package com.example.straterproject.ui.authenticationScreens.signUp

sealed interface SignUpUiEvent {
    object LogInEvent :SignUpUiEvent
    object SignUpWithGoogleEvent:SignUpUiEvent
    object SignUpWithPhoneEvent:SignUpUiEvent
    object SignUpEvent:SignUpUiEvent
}