package com.norm.countries.network

import com.norm.countries.network.model.Country
import com.norm.countries.utils.API_ALL
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

/**
 * Create by Kyaw Zayar Tun on 25/01/2020.
 */
interface CountryApi {
    @GET(API_ALL)
    fun getAllCountriesAsync(): Deferred<List<Country>>
}