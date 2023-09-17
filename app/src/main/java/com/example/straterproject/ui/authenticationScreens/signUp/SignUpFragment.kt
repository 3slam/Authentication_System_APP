package com.example.straterproject.ui.authenticationScreens.signUp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.straterproject.R
import com.example.straterproject.databinding.FragmentSignUpBinding
import com.example.straterproject.ui.base.BaseFragment
import com.example.straterproject.utilities.GOOGLE_ACCOUNT_REQUEST
import com.example.straterproject.utilities.collectLast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SignUpFragment :BaseFragment<FragmentSignUpBinding>() {

    override val layoutIdFragment: Int = R.layout.fragment_sign_up
    override val viewModel: SignUpViewModel by viewModels()

    @Inject
    lateinit var signInClient : GoogleSignInClient

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewmodel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner


        collectLast(viewModel.signUpUIEvent){
            it.getContentIfNotHandled()?.let { onEvent(it) }
        }

    }

    private fun onEvent(event: SignUpUiEvent) {
       when(event){
           SignUpUiEvent.LogInEvent ->  findNavController().navigate(R.id.action_signUpFragment_to_logInFragment)
           SignUpUiEvent.SignUpEvent -> findNavController().navigate(R.id.action_signUpFragment_to_homeFragment)
           SignUpUiEvent.SignUpWithGoogleEvent ->  signInClient.signInIntent.also { startActivityForResult(it, GOOGLE_ACCOUNT_REQUEST) }
           SignUpUiEvent.SignUpWithPhoneEvent -> findNavController().navigate(R.id.action_signUpFragment_to_phoneFragment)
       }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode != Activity.RESULT_CANCELED) {
            if (requestCode == GOOGLE_ACCOUNT_REQUEST) {
                val account = GoogleSignIn.getSignedInAccountFromIntent(data).result
                account?.let {
                    viewModel.signUpWithGoogleAccount(it)
                }
            }
        }
    }

}