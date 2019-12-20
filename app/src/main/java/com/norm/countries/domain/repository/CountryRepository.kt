package com.norm.countries.domain.repository

import com.norm.countries.domain.model.Country
import io.reactivex.Single

/**
 * Create by Kyaw Zayar Tun on 20/12/2019.
 */
interface CountryRepository {
    fun getAllCountries(): Single<List<Country>>
}