package com.norm.countries

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.norm.countries.di.component.DaggerAppComponent

/**
 * Create by Kyaw Zayar Tun on 20/12/2019.
 */
class CountryApp: Application() {
    override fun onCreate() {
        super.onCreate()

        DaggerAppComponent.builder()
            .application(this)
            .build()
            .inject(this)
    }

    override fun attachBaseContext(base: Context?) {
        MultiDex.install(this)
        super.attachBaseContext(base)
    }
}