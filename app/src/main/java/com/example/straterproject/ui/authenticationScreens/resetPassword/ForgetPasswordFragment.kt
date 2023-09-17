package com.example.straterproject.ui.authenticationScreens.resetPassword

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.domain.entity.AuthenticationState
import com.example.straterproject.R
import com.example.straterproject.databinding.FragmentForgetPasswordBinding
import com.example.straterproject.ui.base.BaseFragment
import com.example.straterproject.utilities.collectLast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class ForgetPasswordFragment :BaseFragment<FragmentForgetPasswordBinding>() {
    override val layoutIdFragment: Int = R.layout.fragment_forget_password
    override val viewModel: ForgetPasswordViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        collectLast(viewModel.forgetPasswordUIEvent){
            it.getContentIfNotHandled()?.let { onEvent(it) }
        }

    }

    private fun onEvent(event: ForgetPasswordUiEvent) {
        when (event) {
            ForgetPasswordUiEvent.SendResetPassword -> {
                Toast.makeText(requireContext(), "Check Your Email , Please", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_forgetPasswordFragment_to_logInFragment)
            }
        }
    }

}