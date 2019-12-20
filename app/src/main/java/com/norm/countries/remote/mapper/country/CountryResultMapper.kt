package com.norm.countries.remote.mapper.country

import com.norm.countries.data.entity.CountryEntity
import com.norm.countries.remote.entity.CountryResponse
import com.norm.countries.remote.mapper.Mapper
import javax.inject.Inject

/**
 * Create by Kyaw Zayar Tun on 20/12/2019.
 */
class CountryResultMapper @Inject constructor(): Mapper<CountryResponse, CountryEntity> {
    override fun map(response: CountryResponse): CountryEntity {
        return CountryEntity(response.name)
    }

    fun map(items: List<CountryResponse>): List<CountryEntity> {
        val data = ArrayList<CountryEntity>(items.size)
        for (response in items)
            data.add(map(response))
        return data

    }
}