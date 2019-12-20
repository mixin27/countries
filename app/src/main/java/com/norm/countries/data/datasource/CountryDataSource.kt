package com.norm.countries.data.datasource

import com.norm.countries.data.entity.CountryEntity
import io.reactivex.Single

/**
 * Create by Kyaw Zayar Tun on 20/12/2019.
 */
interface CountryDataSource {
    fun getAllCountries(): Single<List<CountryEntity>>
}