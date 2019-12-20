package com.norm.countries.di.module

import androidx.lifecycle.ViewModelProvider
import com.norm.countries.common.viewmodel.ViewModelProviderFactory
import dagger.Binds
import dagger.Module

/**
 * Create by Kyaw Zayar Tun on 20/12/2019.
 */
@Module
abstract class ViewModelFactoryModule {
    @Binds
    internal abstract fun bindProviderFactory(providerFactory: ViewModelProviderFactory): ViewModelProvider.Factory
}