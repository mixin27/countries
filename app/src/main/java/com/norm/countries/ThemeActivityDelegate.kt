package com.norm.countries

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.norm.countries.domain.result.successOr
import com.norm.countries.domain.settings.ObserveThemeModeUseCase
import com.norm.countries.ui.settings.Theme
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect

/**
 * Interface to implement activity theming via a ViewModel.
 *
 * You can inject a implementation of this via Dagger2, then use the implementation as an interface
 * delegate to add the functionality without writing any code
 *
 * Example usage:
 * ```
 * class MyViewModel @Inject constructor(
 *     themedActivityDelegate: ThemedActivityDelegate
 * ) : ViewModel(), ThemedActivityDelegate by themedActivityDelegate {
 * ```
 */
interface ThemedActivityDelegate {
    /**
     * Allows observing of the current theme
     */
    val theme: LiveData<Theme>
}

class ThemedActivityDelegateImpl constructor(
    private val observeThemeUseCase: ObserveThemeModeUseCase
) : ThemedActivityDelegate {
    @InternalCoroutinesApi
    override val theme: LiveData<Theme> = liveData {
        observeThemeUseCase(Unit).collect {
            emit(it.successOr(Theme.SYSTEM))
        }
    }
}