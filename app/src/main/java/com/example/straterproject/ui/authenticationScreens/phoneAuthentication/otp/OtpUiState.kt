package com.example.straterproject.ui.authenticationScreens.phoneAuthentication.otp


data class OtpUiState (
    val otp :String = "",
    val isLoading:Boolean = false,
    val isOtpValidation: Boolean = false ,
    val isError : Boolean =false ,
    val error:String = "",
    val isLoggedSendSuccessfully:Boolean = false ,
)