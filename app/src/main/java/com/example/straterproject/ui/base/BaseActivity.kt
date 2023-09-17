package com.example.straterproject.ui.base

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.example.straterproject.R
import com.example.straterproject.databinding.ActivityMainBinding

abstract class BaseActivity <VDB : ViewDataBinding>: AppCompatActivity() {

    abstract val activityLayoutId : Int
    private lateinit var _binding : VDB
    val binding : VDB get() =_binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(this,activityLayoutId)
    }
}