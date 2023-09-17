package com.example.straterproject.ui.authenticationScreens.resetPassword

import com.google.android.gms.auth.api.signin.GoogleSignInAccount

data class ForgetPasswordUiState(
    val email :String = "",
    val emailHelperText :String = "",
    val isLoading:Boolean = false,
    val isEmailValidation: Boolean = false ,
    val isError : Boolean =false ,
    val error:String = "",

)
