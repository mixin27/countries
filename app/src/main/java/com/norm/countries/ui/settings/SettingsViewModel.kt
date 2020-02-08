package com.norm.countries.ui.settings

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.norm.countries.data.SharedPreferenceStorage
import com.norm.countries.domain.result.Event
import com.norm.countries.domain.result.successOr
import com.norm.countries.domain.settings.GetAvailableThemeUseCase
import com.norm.countries.domain.settings.GetThemeUseCase
import com.norm.countries.domain.settings.SetThemeUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.launch

@FlowPreview
@ExperimentalCoroutinesApi
class SettingsViewModel(
    private val application: Application
) : ViewModel() {

    private val preferenceStorage = SharedPreferenceStorage(application)

    val setThemeUseCase = SetThemeUseCase(preferenceStorage, Dispatchers.IO)
    val getAvailableThemeUseCase = GetAvailableThemeUseCase(Dispatchers.Default)
    val getThemeUseCase = GetThemeUseCase(preferenceStorage, Dispatchers.IO)

    // Theme setting
    private val _theme = MutableLiveData<Theme>()
    val theme: LiveData<Theme>
        get() = _theme

    // Available themes
    private val _availableThemes = MutableLiveData<List<Theme>>()
    val availableThemes: LiveData<List<Theme>>
        get() = _availableThemes

    private val _navigateToThemeSelector = MutableLiveData<Event<Unit>>()
    val navigateToThemeSelector: LiveData<Event<Unit>>
        get() = _navigateToThemeSelector

    init {
        viewModelScope.launch {
            _theme.value = getThemeUseCase(Unit).successOr(Theme.SYSTEM)
        }

        viewModelScope.launch {
            _availableThemes.value = getAvailableThemeUseCase(Unit).successOr(emptyList())
        }
    }

    fun setTheme(theme: Theme) {
        viewModelScope.launch {
            setThemeUseCase(theme)
        }
    }

    fun onThemeSettingClicked() {
        _navigateToThemeSelector.value = Event(Unit)
    }
}