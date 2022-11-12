package com.lemzeeyyy.countrylistapp.viewmodel

import androidx.lifecycle.LiveData
import com.lemzeeyyy.countrylistapp.repository.CountryRepository
import com.lemzeeyyy.countrylistapp.response.CountryResponse

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