package com.norm.countries.domain.settings

import android.os.Build
import com.norm.countries.data.PreferenceStorage
import com.norm.countries.domain.FlowUseCase
import com.norm.countries.domain.result.Result
import com.norm.countries.ui.settings.Theme
import com.norm.countries.ui.settings.themeFromStorageKey
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

open class ObserveThemeModeUseCase(
    private val preferenceStorage: PreferenceStorage,
    coroutineDispatcher: CoroutineDispatcher
): FlowUseCase<Unit, Theme>(coroutineDispatcher) {
    override fun execute(parameters: Unit): Flow<Result<Theme>> {
        return preferenceStorage.observableSelectedTheme.map {
            val theme = when {
                it != null -> themeFromStorageKey(it)
                // Provide defaults for when there is no theme set
                Build.VERSION.SDK_INT >= 29 -> Theme.SYSTEM
                else -> Theme.BATTERY_SAVER
            }
            Result.Success(theme)
        }
    }
}