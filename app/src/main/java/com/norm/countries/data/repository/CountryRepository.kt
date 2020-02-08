package com.norm.countries.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.norm.countries.data.local.CountryDatabase
import com.norm.countries.data.local.entity.asDomainModel
import com.norm.countries.data.remote.CountryModel
import com.norm.countries.network.ApiService
import com.norm.countries.network.model.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

/**
 * Create by Kyaw Zayar Tun on 28/01/2020.
 */
class CountryRepository(
    private val database: CountryDatabase
) {

    val countries: LiveData<List<CountryModel>> =
        Transformations.map(database.countryDao.getAllCountries()) {
            it.asDomainModel()
        }

    fun getCountry(name: String): LiveData<CountryModel> = Transformations.map(database.countryDao.get(name)) {
        it!!.asDomainModel()
    }

    suspend fun refreshCountries() {
        withContext(Dispatchers.IO) {
            Timber.d("Refresh countries is called")
            val response = ApiService.retrofitService.getAllCountriesAsync().await()
            database.countryDao.insertAll(response.asDatabaseModel())
        }
    }
}