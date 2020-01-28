package com.norm.countries.ui.all

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * Create by Kyaw Zayar Tun on 28/01/2020.
 */
class AllViewModelFactory(
    private val application: Application
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AllViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AllViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class $modelClass")
    }
}