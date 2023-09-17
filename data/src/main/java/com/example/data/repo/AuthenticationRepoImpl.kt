package com.example.data.repo


import com.example.domain.entity.AuthenticationState
import com.example.domain.repo.AuthenticationRepo
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.PhoneAuthCredential
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject


class AuthenticationRepoImpl @Inject constructor(private val auth: FirebaseAuth) : AuthenticationRepo {
    override suspend fun logInWithEmailAndPassword(
        email: String,
        password: String
    ): Flow<AuthenticationState>  = flow {
        try {
            emit(AuthenticationState.Loading)
            auth.signInWithEmailAndPassword(email, password).await()
            emit(AuthenticationState.Success("Sign Up success"))
        }catch (e:Exception){
            emit( AuthenticationState.Error(e.message.toString()))
        }
    }


    override suspend fun signUpWithEmailAndPasswordUseCase(
        email: String,
        password: String
    ) : Flow<AuthenticationState> = flow {
        try {
            emit(AuthenticationState.Loading)
            auth.createUserWithEmailAndPassword(email, password).await()
            emit(AuthenticationState.Success("Sign Up success"))
        }catch (e:Exception){
             emit( AuthenticationState.Error(e.message.toString()))
        }
    }

     override suspend fun resetPassword(email: String):  Flow<AuthenticationState> = flow {
        try {
            emit(AuthenticationState.Loading)
            auth.sendPasswordResetEmail(email).await()
            emit(AuthenticationState.Success("Sign Up success"))
        }catch (e:Exception){
            emit( AuthenticationState.Error(e.message.toString()))
        }
    }


    override suspend fun registerUsingGoogleAccount(account: GoogleSignInAccount):Flow<AuthenticationState> = flow  {
          try {
            emit(AuthenticationState.Loading)
            val credentials = GoogleAuthProvider.getCredential(account.idToken, null)
            auth.signInWithCredential(credentials).await()
              emit(AuthenticationState.Success("Success Enter Using Phone number"))
        } catch (e: Exception) {
              emit(AuthenticationState.Error(e.message.toString()))
        }
    }

    override suspend fun registerUsingPhoneNumber(credential: PhoneAuthCredential):  Flow<AuthenticationState> = flow {
          try {
              emit(AuthenticationState.Loading)
             auth.signInWithCredential(credential).await()
             emit(AuthenticationState.Success("Success Enter Using Phone number"))
        } catch (e: Exception) {
              emit(AuthenticationState.Error(e.message.toString()))
        }
    }


    override suspend fun signOut():  Flow<AuthenticationState> = flow {
          try {
              emit(AuthenticationState.Loading)
            auth.signOut()
                      emit(AuthenticationState.Success("Success Sign Out"))
        } catch (e: Exception) {
              emit(AuthenticationState.Error(e.message.toString()))
        }
    }


}