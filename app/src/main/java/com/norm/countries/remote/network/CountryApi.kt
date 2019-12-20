package com.norm.countries.remote.network

import com.norm.countries.remote.entity.CountryResponse
import retrofit2.Call
import retrofit2.http.GET

/**
 * Create by Kyaw Zayar Tun on 20/12/2019.
 */
interface CountryApi {
    @GET("all")
    fun getAllCountries(): Call<List<CountryResponse>>
}