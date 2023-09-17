package com.example.straterproject.ui.authenticationScreens.phoneAuthentication.otp

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.straterproject.R
import com.example.straterproject.databinding.FragmentOtpBinding
import com.example.straterproject.ui.base.BaseFragment
import com.example.straterproject.utilities.collectLast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class OtpFragment : BaseFragment<FragmentOtpBinding> (){

    override val layoutIdFragment: Int = R.layout.fragment_otp
    override val viewModel: OtpViewModel  by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
         binding.viewmodel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner


        collectLast(viewModel.otpUIEvent){
            it.getContentIfNotHandled()?.let { onEvent(it) }
        }

    }

    private fun onEvent(event: OtpUiEvent) {
        when(event){
            OtpUiEvent.Verification -> findNavController().navigate(R.id.action_otpFragment_to_homeFragment)
        }
    }

}