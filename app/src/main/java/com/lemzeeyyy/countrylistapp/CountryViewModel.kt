package com.lemzeeyyy.countrylistapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lemzeeyyy.countrylistapp.model.CountryResponse

class CountryViewModel  {
    private var countryRepository: CountryRepository? = null

    constructor() {
        countryRepository = CountryRepository.getInstance()
    }

    fun searchCountryApi(query: String?) {
        countryRepository!!.setCountryApi(query!!)
    }

    fun getCountries(): LiveData<List<CountryResponse>?> {
        return countryRepository!!.getCountries()
    }

    fun setAllCountries(){
        countryRepository!!.setAllCountriesApi()
    }

    fun getAllCountries(): LiveData<List<CountryResponse>?>{
        return countryRepository!!.getAllCountries()
    }
}