package com.norm.countries.data.repository

import com.norm.countries.data.datasource.CountryDataSource
import com.norm.countries.data.mapper.country.CountryEntityMapper
import com.norm.countries.domain.model.Country
import com.norm.countries.domain.repository.CountryRepository
import io.reactivex.Single

/**
 * Create by Kyaw Zayar Tun on 20/12/2019.
 */
class CountryRepositoryImpl(
    private val countryDataSource: CountryDataSource,
    private val countryEntityMapper: CountryEntityMapper
) : CountryRepository {
    override fun getAllCountries(): Single<List<Country>> {
        return countryDataSource.getAllCountries().map(countryEntityMapper::map)
    }
}