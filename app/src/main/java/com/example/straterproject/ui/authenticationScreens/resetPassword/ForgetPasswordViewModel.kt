package com.example.straterproject.ui.authenticationScreens.resetPassword

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.AuthenticationState
import com.example.domain.usecases.ResetPasswordUseCase
import com.example.straterproject.ui.authenticationScreens.logIn.LogInUiEvent
import com.example.straterproject.ui.base.BaseViewModel
import com.example.straterproject.utilities.Event
import com.example.straterproject.utilities.InputValidationState
import com.example.straterproject.utilities.InputValidator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForgetPasswordViewModel @Inject constructor(
    private val resetPasswordUseCase: ResetPasswordUseCase
) :BaseViewModel(){

    private val _forgetPasswordUIState = MutableStateFlow(ForgetPasswordUiState())
    val forgetPasswordUIState = _forgetPasswordUIState.asStateFlow()

    private val _forgetPasswordUIEvent  = MutableStateFlow<Event<ForgetPasswordUiEvent?>>(Event(null))
    val  forgetPasswordUIEvent = _forgetPasswordUIEvent.asStateFlow()

    fun resetUiState() = _forgetPasswordUIState.update {ForgetPasswordUiState()}
    fun onClickResetPassword() = resetPassword()

    private fun resetPassword() = viewModelScope.launch {
        _forgetPasswordUIState.update {it.copy(isLoading = true)}
         resetPasswordUseCase.execute(_forgetPasswordUIState.value.email).collectLatest {state->
             when(state){
                 AuthenticationState.Loading  -> {
                     _forgetPasswordUIState.update { it.copy(isLoading = true) }
                 }
                 is AuthenticationState.Error -> {
                     _forgetPasswordUIState.update {
                         it.copy(
                             isError = true,
                             error = state.message,
                             isLoading = false
                         )
                     }
                 }
                 is AuthenticationState.Success -> {
                     _forgetPasswordUIEvent.update { Event(ForgetPasswordUiEvent.SendResetPassword) }
                 }
             }
         }

    }

    fun onEmailInputChange(email :CharSequence){
        val emailValidationState = InputValidator.checkEmailValidation(email.toString())
        _forgetPasswordUIState.update {
            it.copy(
                email = email.toString() ,
                emailHelperText = if (emailValidationState is InputValidationState.InValid){
                    emailValidationState.message
                }else{
                    ""
                } ,
                isEmailValidation = emailValidationState is InputValidationState.Valid
            )
        }
    }




}