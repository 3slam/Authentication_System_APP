package com.example.domain.usecases

import com.example.domain.repo.AuthenticationRepo
import com.google.firebase.auth.PhoneAuthCredential
import javax.inject.Inject

class RegisterUsingPhoneNumberUseCase @Inject constructor (
    private val authenticationRepo: AuthenticationRepo
) {
    suspend fun execute( credential: PhoneAuthCredential) = authenticationRepo.registerUsingPhoneNumber(credential)
}