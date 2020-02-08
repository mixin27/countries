package com.norm.countries.ui.all

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.norm.countries.data.local.CountryDatabase
import com.norm.countries.data.remote.CountryModel
import com.norm.countries.data.repository.CountryRepository
import com.norm.countries.network.Status
import com.norm.countries.network.model.Country
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.io.IOException

/**
 * Create by Kyaw Zayar Tun on 28/01/2020.
 */
class AllViewModel(
    application: Application
) : ViewModel() {

    private val countryRepository = CountryRepository(CountryDatabase.getInstance(application))
    val countries = countryRepository.countries

    private var viewModelJob = Job()
    private var uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _status = MutableLiveData<Status>()
    val status: LiveData<Status>
        get() = _status

    private val _navigateToSelectedItem = MutableLiveData<CountryModel>()
    val navigateToSelectedItem: LiveData<CountryModel>
        get() = _navigateToSelectedItem

    init {
        fetchAllCountries()
    }

    private fun fetchAllCountries() {
        uiScope.launch {
            try {
                _status.value = Status.LOADING
                countryRepository.refreshCountries()
                _status.value = Status.SUCCESS
            } catch (e: IOException) {
                if (countries.value!!.isEmpty()) {
                    _status.value = Status.ERROR
                } else {
                    _status.value = Status.SUCCESS
                }
            }
        }
    }

    /**
     * When the property is clicked, set the [_navigateToSelectedItem] [MutableLiveData]
     * @param country The [CountryModel] that was clicked on.
     */
    fun displayCountryDetails(country: CountryModel) {
        _navigateToSelectedItem.value = country
    }

    /**
     * After the navigation has taken place, make sure navigateToSelectedItem is set to null
     */
    fun displayCountryDetailsComplete() {
        _navigateToSelectedItem.value = null
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}