package com.norm.countries.di.module

import com.norm.countries.data.repository.CountryRepositoryImpl
import com.norm.countries.domain.repository.CountryRepository
import com.norm.countries.remote.di.NetworkDataSourceModule
import dagger.Binds
import dagger.Module

/**
 * Create by Kyaw Zayar Tun on 20/12/2019.
 */
@Module(includes = [NetworkDataSourceModule::class])
abstract class RepositoryModule {
    @Binds
    abstract fun countryRepository(countryRepositoryImpl: CountryRepositoryImpl): CountryRepository
}