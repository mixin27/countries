package com.norm.countries.data.mapper.country

import com.norm.countries.data.entity.CountryEntity
import com.norm.countries.data.mapper.Mapper
import com.norm.countries.domain.model.Country
import javax.inject.Inject

/**
 * Create by Kyaw Zayar Tun on 20/12/2019.
 */
class CountryEntityMapper
@Inject constructor() : Mapper<Country, CountryEntity>() {

    override fun map(entity: CountryEntity): Country {
        return Country.success(entity.name)
    }

    fun map(items: List<CountryEntity>): List<Country> {
        val data = ArrayList<Country>(items.size)
        for (response in items)
            data.add(map(response))
        return data
    }
}