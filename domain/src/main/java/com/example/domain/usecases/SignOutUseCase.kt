package com.example.domain.usecases

import com.example.domain.repo.AuthenticationRepo
import javax.inject.Inject

class SignOutUseCase @Inject constructor(
    private val authenticationRepo: AuthenticationRepo
) {
    suspend fun execute() = authenticationRepo.signOut()
}