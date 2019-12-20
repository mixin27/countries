package com.norm.countries.remote.datasource

import com.norm.countries.data.datasource.CountryDataSource
import com.norm.countries.data.entity.CountryEntity
import com.norm.countries.data.exception.DataException
import com.norm.countries.data.exception.Issue
import com.norm.countries.remote.mapper.country.CountryResultMapper
import com.norm.countries.remote.network.CountryApi
import io.reactivex.Single
import java.io.IOException
import javax.inject.Inject

/**
 * Create by Kyaw Zayar Tun on 20/12/2019.
 */
class CountryDataSourceImpl
@Inject constructor(
    private val countryApi: CountryApi,
    private val countryResultMapper: CountryResultMapper
) : CountryDataSource {
    override fun getAllCountries(): Single<List<CountryEntity>> {
        return Single.defer<List<CountryEntity>> {
            try {
                val response = countryApi.getAllCountries().execute()
                if (response.isSuccessful) {
                    return@defer Single.just(countryResultMapper.map(response.body()!!))
                } else {
                    return@defer Single.error(DataException(Issue.API))
                }
            } catch (ex: IOException) {
                return@defer Single.error(DataException(Issue.NETWORK))
            }
        }
    }
}