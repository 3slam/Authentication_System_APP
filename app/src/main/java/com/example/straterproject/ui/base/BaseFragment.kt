package com.example.straterproject.ui.base

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.example.domain.entity.AuthenticationState
import com.example.straterproject.R
import com.example.straterproject.ui.authenticationScreens.phoneAuthentication.otp.OtpUiEvent
import com.google.android.material.bottomsheet.BottomSheetDialog

abstract class BaseFragment<VDB : ViewDataBinding> : Fragment() {

    abstract val layoutIdFragment: Int
    abstract val viewModel: ViewModel
    private lateinit var _binding: VDB
    protected val binding: VDB get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = DataBindingUtil.inflate(inflater, layoutIdFragment, container, false)
        return _binding.root

    }



}

