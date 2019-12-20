package com.norm.countries.remote.di

import com.norm.countries.BuildConfig
import com.norm.countries.remote.network.CountryApi
import com.norm.countries.remote.network.NullToEmptyStringAdapter
import com.norm.countries.remote.network.interceptor.ResponseInterceptor
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

/**
 * Create by Kyaw Zayar Tun on 20/12/2019.
 */
@Module
class RetrofitModule {

    @Provides
    @Singleton
    fun okHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
        val loggingInterceptor = HttpLoggingInterceptor()

        if (BuildConfig.DEBUG) {
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        } else {
            loggingInterceptor.level = HttpLoggingInterceptor.Level.NONE
        }

        builder.addInterceptor(ResponseInterceptor())
            .addInterceptor(loggingInterceptor)
        return builder.build()
    }

    @Provides
    @Singleton
    fun moshi(): Moshi {
        return Moshi.Builder()
            .add(NullToEmptyStringAdapter())
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun retrofit(moshi: Moshi, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("")
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @Provides
    @Singleton
    fun countryService(retrofit: Retrofit): CountryApi {
        return retrofit.create(CountryApi::class.java)
    }
}