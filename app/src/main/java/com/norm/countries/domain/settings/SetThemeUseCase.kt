package com.norm.countries.domain.settings

import com.norm.countries.data.PreferenceStorage
import com.norm.countries.domain.UseCase
import com.norm.countries.ui.settings.Theme
import kotlinx.coroutines.CoroutineDispatcher

open class SetThemeUseCase(
    private val preferenceStorage: PreferenceStorage,
    coroutineDispatcher: CoroutineDispatcher
): UseCase<Theme, Unit>(coroutineDispatcher) {
    override fun execute(parameters: Theme) {
        preferenceStorage.selectedTheme = parameters.storageKey
    }
}