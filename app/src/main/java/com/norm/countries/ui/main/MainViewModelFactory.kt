package com.norm.countries.ui.main

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.norm.countries.ThemedActivityDelegate
import com.norm.countries.ui.all.AllViewModel
import com.norm.countries.ui.detail.CountryDetailsViewModel
import com.norm.countries.ui.settings.SettingsViewModel

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(
    private val themedActivityDelegate: ThemedActivityDelegate
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        with(modelClass) {
            when {
                isAssignableFrom(MainActivityViewModel::class.java) -> {
                    MainActivityViewModel(themedActivityDelegate)
                }
                else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        } as T
}