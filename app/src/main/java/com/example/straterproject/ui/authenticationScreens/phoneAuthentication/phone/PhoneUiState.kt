package com.example.straterproject.ui.authenticationScreens.phoneAuthentication.phone


data class PhoneUiState (
    val phone :String = "",
    val isLoading:Boolean = false,
    val isPhoneValidation: Boolean = false ,
    val isError : Boolean =false ,
    val error:String = "",
    val verificationId:String = "",
    val isCodeSendSuccessfully:Boolean = false ,
)