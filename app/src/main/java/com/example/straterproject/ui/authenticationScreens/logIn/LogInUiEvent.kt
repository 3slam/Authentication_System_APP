package com.example.straterproject.ui.authenticationScreens.logIn

sealed interface LogInUiEvent {
    object LogInEvent :LogInUiEvent
    object ForgetPasswordEvent:LogInUiEvent
    object LogInWithGoogleEvent:LogInUiEvent
    object LogInWithPhoneEvent:LogInUiEvent
    object SignUpEvent:LogInUiEvent
}