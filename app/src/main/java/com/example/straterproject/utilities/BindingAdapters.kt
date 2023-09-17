package com.example.straterproject.utilities

import android.graphics.Color
import android.graphics.Color.GREEN
import android.graphics.Color.RED
import android.view.View
import androidx.databinding.BindingAdapter
import com.example.straterproject.R
import com.google.android.material.textfield.TextInputLayout


@BindingAdapter("app:isVisibleOrInVisible")
fun isVisibleOrInVisible(view: View,isVisible:Boolean) {
    view.visibility =  if (isVisible) View.VISIBLE else View.INVISIBLE
}

@BindingAdapter("app:isVisibleOrGone")
fun isVisibleOrGone(view: View,isVisible:Boolean) {
    view.visibility =  if (isVisible) View.VISIBLE else View.GONE
}

@BindingAdapter("app:isButtonEnabled")
fun isButtonEnabled(view: View , isLoading : Boolean , isError :Boolean ) {
    view.isEnabled = !(isLoading || isError)
}

