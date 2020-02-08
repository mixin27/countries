package com.norm.countries.domain.settings

import android.os.Build
import com.norm.countries.domain.UseCase
import com.norm.countries.ui.settings.Theme
import kotlinx.coroutines.CoroutineDispatcher

class GetAvailableThemeUseCase(
    coroutineDispatcher: CoroutineDispatcher
): UseCase<Unit, List<Theme>>(coroutineDispatcher) {
    override fun execute(parameters: Unit): List<Theme> {
        return if (Build.VERSION.SDK_INT >= 29) {
            listOf(Theme.LIGHT, Theme.DARK, Theme.SYSTEM)
        } else {
            listOf(Theme.LIGHT, Theme.DARK, Theme.BATTERY_SAVER)
        }
    }
}