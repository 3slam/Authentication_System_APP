package com.example.straterproject.ui.authenticationScreens.phoneAuthentication.phone

import com.example.straterproject.ui.base.BaseViewModel
import com.example.straterproject.utilities.Event
import com.example.straterproject.utilities.InputValidator
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SendOtpUsingPhoneNumberViewModel @Inject constructor ():BaseViewModel(){

     private val _phoneUIState = MutableStateFlow(PhoneUiState())
    val  phoneUIState = _phoneUIState.asStateFlow()

    private val _phoneUIEvent  = MutableStateFlow<Event<PhoneUiEvent?>>(Event(null))
    val phoneUIEvent = _phoneUIEvent.asStateFlow()


   val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        override fun onVerificationCompleted(credential: PhoneAuthCredential) = Unit
        override fun onVerificationFailed(e: FirebaseException) {
            _phoneUIState.update { it.copy(isError = true , error = e.message.toString()) }
        }

        override fun onCodeSent(verificationId: String, token: PhoneAuthProvider.ForceResendingToken) {
            _phoneUIEvent.update {
                _phoneUIState.update { it.copy(isLoading = false) }
                Event(PhoneUiEvent.CodeSent(verificationId))
            }
        }
    }


    fun onPhoneInputChange(text: CharSequence) {
        _phoneUIState.update {
            it.copy(
                isPhoneValidation =  InputValidator.checkPhoneNumberValidation(text.toString()) ,
                phone = text.toString()
            )
        }
    }

    fun onClickSendCodeVerification() {
        _phoneUIState.update { it.copy(isLoading = true) }
        _phoneUIEvent.update { Event(PhoneUiEvent.SendVerifactionCode(_phoneUIState.value.phone)) }
    }

    fun resetPhoneUiState(){
        _phoneUIState.update {PhoneUiState()}
    }

}