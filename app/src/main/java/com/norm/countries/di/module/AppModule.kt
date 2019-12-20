package com.norm.countries.di.module

import android.content.Context
import com.norm.countries.CountryApp
import com.norm.countries.common.UIThread
import com.norm.countries.data.executor.JobExecutor
import com.norm.countries.domain.executor.PostExecutionThread
import com.norm.countries.domain.executor.ThreadExecutor
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Create by Kyaw Zayar Tun on 20/12/2019.
 */
@Module(includes = [AppModule.Provider::class, ViewModelFactoryModule::class])
abstract class AppModule {

    @Binds
    internal abstract fun bindPostExecutionThread(uiThread: UIThread): PostExecutionThread

    @Binds
    internal abstract fun provideThreadExecutor(jobExecutor: JobExecutor): ThreadExecutor

    @Module
    object Provider {
        @Provides
        @JvmStatic
        @Singleton
        fun context(application: CountryApp): Context {
            return application
        }
    }
}