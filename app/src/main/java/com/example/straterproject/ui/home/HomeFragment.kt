package com.example.straterproject.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.example.straterproject.R
import com.example.straterproject.databinding.HomeFragmentBinding
import com.example.straterproject.ui.base.BaseFragment
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment :BaseFragment<HomeFragmentBinding> (){
    override val layoutIdFragment  = R.layout.home_fragment
    override val viewModel: HomeViewModel by  viewModels()

    @Inject
    lateinit var auth: FirebaseAuth

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.logOut.setOnClickListener {
            auth.signOut()
            findNavController().navigate(R.id.action_homeFragment_to_logInFragment)
        }

    }
}