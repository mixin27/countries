@file:JvmName("ExtensionUtils")

package com.norm.countries.utils.extensions

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatDelegate
import com.norm.countries.ui.settings.Theme
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform

/**
 * Create by Kyaw Zayar Tun on 25/01/2020.
 */
fun ViewGroup.inflate(@LayoutRes layoutId: Int, attachedToBase: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutId, this, attachedToBase)
}

/**
 * Flow transformation that ignores the first element emitted by the original Flow.
 */
fun <T> Flow<T>.ignoreFirst(): Flow<T> {
    var firstElement = true
    return transform { value ->
        if (firstElement) {
            firstElement = false
            return@transform
        } else {
            return@transform emit(value)
        }
    }
}

/**
 * Cancel the Job if it's active.
 */
fun Job?.cancelIfActive() {
    if (this?.isActive == true) {
        cancel()
    }
}

@SuppressLint("WrongConstant") // Suppressing warning on MODE_NIGHT_AUTO_BATTERY. b/128789886
fun updateForTheme(theme: Theme) = when (theme) {
    Theme.DARK -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
    Theme.LIGHT -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    Theme.SYSTEM -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
    Theme.BATTERY_SAVER -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY)
}