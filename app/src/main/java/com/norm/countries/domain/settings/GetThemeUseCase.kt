package com.norm.countries.domain.settings

import android.os.Build
import com.norm.countries.data.PreferenceStorage
import com.norm.countries.domain.UseCase
import com.norm.countries.ui.settings.Theme
import com.norm.countries.ui.settings.themeFromStorageKey
import kotlinx.coroutines.CoroutineDispatcher

class GetThemeUseCase(
    private val preferenceStorage: PreferenceStorage,
    coroutineDispatcher: CoroutineDispatcher
): UseCase<Unit, Theme>(coroutineDispatcher) {
    override fun execute(parameters: Unit): Theme {
        preferenceStorage.selectedTheme?.let {
            return themeFromStorageKey(it)
        }
        // We don't currently have a theme set, so provide a default
        return if (Build.VERSION.SDK_INT >= 29) Theme.SYSTEM else Theme.BATTERY_SAVER
    }
}