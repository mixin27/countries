package com.norm.countries.data

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import androidx.annotation.WorkerThread
import androidx.core.content.edit
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

interface PreferenceStorage {
    var selectedTheme: String?
    var observableSelectedTheme: Flow<String?>
}

/**
 * [PreferenceStorage] impl backed by [android.content.SharedPreferences].
 */
@ExperimentalCoroutinesApi
@FlowPreview
class SharedPreferenceStorage(context: Context): PreferenceStorage {

    private val selectedThemeChannel: ConflatedBroadcastChannel<String?> by lazy {
        ConflatedBroadcastChannel<String?>().also { channel ->
            channel.offer(selectedTheme)
        }
    }

    private val changeListener = SharedPreferences.OnSharedPreferenceChangeListener { _, key ->
        when (key) {
            PREF_DARK_MODE_ENABLED -> selectedThemeChannel.offer(selectedTheme)
        }
    }

    private val prefs: Lazy<SharedPreferences> = lazy { // Lazy to prevent IO access to main thread.
        context.applicationContext.getSharedPreferences(
            PREFS_NAME, MODE_PRIVATE
        ).apply {
            registerOnSharedPreferenceChangeListener(changeListener)
        }
    }

    override var selectedTheme by StringPreference(prefs, PREF_DARK_MODE_ENABLED, null)

    override var observableSelectedTheme: Flow<String?>
        get() = selectedThemeChannel.asFlow()
        set(_) = throw IllegalAccessException("This property can't be changed")

    companion object {
        const val PREFS_NAME = "countries"
        const val PREF_DARK_MODE_ENABLED = "pref_dark_mode"
    }

    fun registerOnPreferenceChangeListener(listener: SharedPreferences.OnSharedPreferenceChangeListener) {
        prefs.value.registerOnSharedPreferenceChangeListener(listener)
    }
}

/**
 * Boolean
 */
class BooleanPreference(
    private val preferences: Lazy<SharedPreferences>,
    private val name: String,
    private val defaultValue: Boolean
) : ReadWriteProperty<Any, Boolean> {

    @WorkerThread
    override fun getValue(thisRef: Any, property: KProperty<*>): Boolean {
        return preferences.value.getBoolean(name, defaultValue)
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: Boolean) {
        preferences.value.edit { putBoolean(name, value) }
    }
}

/**
 * String
 */
class StringPreference(
    private val preferences: Lazy<SharedPreferences>,
    private val name: String,
    private val defaultValue: String?
) : ReadWriteProperty<Any, String?> {

    @WorkerThread
    override fun getValue(thisRef: Any, property: KProperty<*>): String? {
        return preferences.value.getString(name, defaultValue)
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: String?) {
        preferences.value.edit { putString(name, value) }
    }
}