package com.example.domain.repo

import com.example.domain.entity.AuthenticationState
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.PhoneAuthCredential
import kotlinx.coroutines.flow.Flow


interface AuthenticationRepo {

    suspend fun logInWithEmailAndPassword(email :String , password : String) : Flow<AuthenticationState>
    suspend fun signUpWithEmailAndPasswordUseCase(email :String , password : String) :  Flow<AuthenticationState>
    suspend fun resetPassword(email :String) : Flow<AuthenticationState>
    suspend fun registerUsingGoogleAccount(account: GoogleSignInAccount) :  Flow<AuthenticationState>
    suspend fun registerUsingPhoneNumber(credential: PhoneAuthCredential) :  Flow<AuthenticationState>
    suspend fun signOut():  Flow<AuthenticationState>

}