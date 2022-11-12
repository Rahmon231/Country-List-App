package com.lemzeeyyy.countrylistapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lemzeeyyy.countrylistapp.API.CountryApiClient
import com.lemzeeyyy.countrylistapp.model.CountryResponse

class CountryRepository {

    private lateinit var countryApiClient: CountryApiClient

    private var mQuery: String? = null

    constructor() {
        countryApiClient = CountryApiClient.getInstance()
    }

    companion object{
        private var instance: CountryRepository? = null
       fun getInstance() : CountryRepository{
           if (instance == null){
               instance = CountryRepository()
           }
           return instance as CountryRepository
       }
    }


    fun getCountries(): LiveData<List<CountryResponse>?> {
        return countryApiClient.getCountries()
    }

    fun setCountryApi(query: String) {
        mQuery = query
        CountryApiClient.getInstance()!!.searchCountryApi(query)
    }

    fun setAllCountriesApi(){
        CountryApiClient.getInstance().getAllCountriesApi()
    }

    fun getAllCountries(): LiveData<List<CountryResponse>?>{
        return countryApiClient.getAllCountries()
    }


}