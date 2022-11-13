package com.lemzeeyyy.countrylistapp.API

import com.lemzeeyyy.countrylistapp.response.CountryResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface CountryApi {

    @GET("v3.1/name/{name}")
    fun searchCountries(
        @Path("name") query: String?
    ): Call<List<CountryResponse>>

    @GET("v3.1/all")
    fun getAllCountries (): Call<List<CountryResponse>>

    @GET("v3.1/lang/{lang}")
    fun searchByLanguage(
        @Path("lang") query: String?
    ):Call<List<CountryResponse>>
}