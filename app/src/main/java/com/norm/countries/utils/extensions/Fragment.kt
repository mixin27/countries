package com.norm.countries.utils.extensions

import androidx.fragment.app.Fragment
import com.norm.countries.utils.ViewModelFactory

fun Fragment.getViewModelFactory(): ViewModelFactory {
    val application = requireActivity().application
    return ViewModelFactory(application)
}