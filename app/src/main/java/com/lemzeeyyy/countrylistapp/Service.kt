package com.lemzeeyyy.countrylistapp

import android.net.wifi.hotspot2.pps.Credential
import com.lemzeeyyy.countrylistapp.API.CountryApi
import com.lemzeeyyy.countrylistapp.utils.Credentials
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Service {
    private val retrofitBuilder: Retrofit.Builder = Retrofit.Builder()
        .baseUrl(Credentials.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())

    private val retrofit = retrofitBuilder.build()
    private val countryApi: CountryApi = retrofit.create(CountryApi::class.java)

    fun getCountryApi(): CountryApi? {
        return countryApi
    }
}