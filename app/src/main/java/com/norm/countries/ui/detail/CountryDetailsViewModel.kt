package com.norm.countries.ui.detail

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.norm.countries.data.local.CountryDatabase
import com.norm.countries.data.remote.CountryModel
import com.norm.countries.data.repository.CountryRepository

class CountryDetailsViewModel(
    application: Application
) : ViewModel() {
    private val countryRepository = CountryRepository(CountryDatabase.getInstance(application))

    private val _country = MutableLiveData<CountryModel>()
    val country: LiveData<CountryModel> = _country

    fun getCountry(name: String?) {
//        _country.value = countryRepository.getCountry(name!!).value
    }
}