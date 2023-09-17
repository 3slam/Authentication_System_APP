package com.example.straterproject.ui.authenticationScreens.phoneAuthentication.otp


import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.AuthenticationState
import com.example.domain.usecases.RegisterUsingPhoneNumberUseCase
import com.example.straterproject.ui.base.BaseViewModel
import com.example.straterproject.utilities.Event
import com.example.straterproject.utilities.InputValidator
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class OtpViewModel @Inject constructor(
    private val registerUsingPhoneNumberUseCase: RegisterUsingPhoneNumberUseCase,
    savedStateHandle: SavedStateHandle
): BaseViewModel() {

    private val _otpUIState = MutableStateFlow(OtpUiState())
    val  otpUIState = _otpUIState.asStateFlow()

    private val _otpUIEvent  = MutableStateFlow<Event<OtpUiEvent?>>(Event(null))
    val otpUIEvent = _otpUIEvent.asStateFlow()

    private var storedVerificationId :String

    init {
        storedVerificationId = savedStateHandle.get<String>("verificationId") ?: ""
     }

    fun onOtpInputChange(text: CharSequence) {
        _otpUIState.update {
            it.copy(
                isOtpValidation =  InputValidator.checkOtpCodeValidation(text.toString()) ,
                otp = text.toString()
            )
        }
    }

    fun onClickVerification() = verification()

    fun resetUiState() = _otpUIState.update { OtpUiState() }

    private fun verification() =viewModelScope.launch {

        _otpUIState.update { it.copy(isLoading = true) }
        val credential : PhoneAuthCredential = PhoneAuthProvider.getCredential(storedVerificationId,_otpUIState.value.otp)
        registerUsingPhoneNumberUseCase.execute(credential).collectLatest {state->
            when(state) {
                AuthenticationState.Loading -> _otpUIState.update { it.copy(isLoading = true) }
                is AuthenticationState.Error -> _otpUIState.update {
                    it.copy(
                        isLoading = false,
                        error = state.message,
                        isError = true
                    )
                }
                is AuthenticationState.Success -> {
                    _otpUIState.update { it.copy(isLoading = false, isLoggedSendSuccessfully = true) }
                    _otpUIEvent.update { Event(OtpUiEvent.Verification) }
                }
            }
        }

       }




}
