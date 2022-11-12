package com.lemzeeyyy.countrylistapp.API

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lemzeeyyy.countrylistapp.Service
import com.lemzeeyyy.countrylistapp.model.CountryResponse
import retrofit2.Call
import retrofit2.Response
import java.io.IOException
import java.util.concurrent.Future
import java.util.concurrent.TimeUnit

class CountryApiClient {
     private var countryObject: MutableLiveData<List<CountryResponse>?>

     private var allCountriesObject: MutableLiveData<List<CountryResponse>?>

    private var retrieveCountryRunnable: RetrieveCountryRunnable? = null
    private var allCountryRunnable: RetrieveAllCountryRunnable? = null

    constructor() {
        allCountriesObject = MutableLiveData()
        countryObject = MutableLiveData()

    }

    companion object{
        private var instance: CountryApiClient? = null
        fun getInstance(): CountryApiClient {
            if (instance == null) {
                instance = CountryApiClient()
            }
            return instance as CountryApiClient
        }
    }



    fun getCountries(): LiveData<List<CountryResponse>?> {
        return countryObject
    }

    fun getAllCountries(): LiveData<List<CountryResponse>?>{
        return allCountriesObject
    }


    fun searchCountryApi(query: String) {
        if (retrieveCountryRunnable != null) {
            retrieveCountryRunnable = null
        }
        retrieveCountryRunnable = RetrieveCountryRunnable(query)

        val myHandler: Future<*> =
            AppExecutor.getInstance().networkIO()!!.submit(retrieveCountryRunnable)
        AppExecutor.getInstance().networkIO()!!.schedule(Runnable { //Canceling the retrofit call
            myHandler.cancel(true)
        }, 5000, TimeUnit.MILLISECONDS)
    }

    fun getAllCountriesApi(){
        if(allCountryRunnable != null){
            allCountryRunnable = null
        }
        allCountryRunnable = RetrieveAllCountryRunnable()

        val myHandler: Future<*> =
            AppExecutor.getInstance().networkIO()!!.submit(allCountryRunnable)
        AppExecutor.getInstance().networkIO()!!.schedule(Runnable { //Canceling the retrofit call
            myHandler.cancel(true)
        }, 5000, TimeUnit.MILLISECONDS)

    }

    private inner class RetrieveCountryRunnable : Runnable{
        lateinit var  query: String
         var cancelRequest: Boolean=false


        constructor(query: String) {
            this.query = query
            cancelRequest = false;
        }

        override fun run() {
            try {
                val response: Response<*> = getSearchedCountry(query).execute()

                if (cancelRequest) {
                    return
                }

                if (response.code() == 200) {
                    val countryResponse = response.body() as List<CountryResponse>?
                    countryObject.postValue(countryResponse)
                }

                else {
                    Log.d("CountryClientApiError", "run: ${response.errorBody().toString()}")
                }

            }catch (e : IOException){
            e.printStackTrace()
                countryObject.postValue(null)

        }

        }
        fun getSearchedCountry(query: String): Call<List<CountryResponse>> {
            return Service.getCountryApi().searchCountries(query);
        }


        private fun setCancelRequest() {
            Log.d("CancelReq", "setCancelRequest: Cancelling Search Request")
            cancelRequest = true
        }


    }

    private inner class RetrieveAllCountryRunnable : Runnable{

        var cancelRequest: Boolean=false


        constructor() {
            cancelRequest = false;
        }

        override fun run() {
            try {
                val response: Response<*> = getAllCountries().execute()

                if (cancelRequest) {
                    return
                }

                if (response.code() == 200) {
                    val allResponse = response.body() as List<CountryResponse>?
                    allCountriesObject.postValue(allResponse)
                }

                else {
                    Log.d("CountryClientApiError", "run: ${response.errorBody().toString()}")
                }

            }catch (e : IOException){
                e.printStackTrace()
                allCountriesObject.postValue(null)

            }

        }

        fun getAllCountries(): Call<List<CountryResponse>> {
            return Service.getCountryApi().getAllCountries();
        }

        private fun setCancelRequest() {
            Log.d("CancelReq", "setCancelRequest: Cancelling Search Request")
            cancelRequest = true
        }


    }

}