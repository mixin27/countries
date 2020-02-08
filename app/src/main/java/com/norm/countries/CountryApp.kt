package com.norm.countries

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.jakewharton.threetenabp.AndroidThreeTen
import leakcanary.AppWatcher
import timber.log.Timber

/**
 * Create by Kyaw Zayar Tun on 20/12/2019.
 */
class CountryApp: Application() {
    override fun onCreate() {
        super.onCreate()

        // Leakcanary
        if (BuildConfig.DEBUG) {
            AppWatcher.config = AppWatcher.config.copy(watchActivities = false)

            // Timber
            Timber.plant(Timber.DebugTree())
        }

        // ThreeTenABP
        AndroidThreeTen.init(this)
    }

    override fun attachBaseContext(base: Context?) {
        MultiDex.install(this)
        super.attachBaseContext(base)
    }
}