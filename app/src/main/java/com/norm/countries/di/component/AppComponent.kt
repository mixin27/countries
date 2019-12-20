package com.norm.countries.di.component

import com.norm.countries.CountryApp
import com.norm.countries.di.module.AppModule
import com.norm.countries.di.module.RepositoryModule
import com.norm.countries.di.module.ViewModelFactoryModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

/**
 * Create by Kyaw Zayar Tun on 20/12/2019.
 */
@Singleton
@Component(modules = [AppModule::class, ViewModelFactoryModule::class, RepositoryModule::class])
interface AppComponent {

    fun inject(app: CountryApp)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: CountryApp): Builder

        fun build(): AppComponent
    }
}