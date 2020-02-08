package com.norm.countries.utils

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.norm.countries.ui.all.AllViewModel
import com.norm.countries.ui.detail.CountryDetailsViewModel
import com.norm.countries.ui.main.MainActivityViewModel
import com.norm.countries.ui.settings.SettingsViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(
    private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        with(modelClass) {
            when {
                isAssignableFrom(AllViewModel::class.java) -> {
                    AllViewModel(application)
                }
                isAssignableFrom(CountryDetailsViewModel::class.java) -> {
                    CountryDetailsViewModel(application)
                }
                isAssignableFrom(SettingsViewModel::class.java) -> {
                    SettingsViewModel(application)
                }
                else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        } as T
}