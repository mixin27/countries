package com.norm.countries.remote.di

import com.norm.countries.data.datasource.CountryDataSource
import com.norm.countries.remote.datasource.CountryDataSourceImpl
import dagger.Binds
import dagger.Module

/**
 * Create by Kyaw Zayar Tun on 20/12/2019.
 */
@Module(includes = [RetrofitModule::class])
abstract class NetworkDataSourceModule {
    @Binds
    abstract fun countryNetworkDataSource(countryDataSourceImpl: CountryDataSourceImpl): CountryDataSource
}