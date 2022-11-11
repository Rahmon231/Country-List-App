package com.lemzeeyyy.countrylistapp.API

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.lemzeeyyy.countrylistapp.Service
import com.lemzeeyyy.countrylistapp.model.CountryResponse
import retrofit2.Call
import retrofit2.Response
import java.io.IOException
import java.util.concurrent.Future
import java.util.concurrent.TimeUnit

class CountryApiClient {
     private lateinit var countryObject: MutableLiveData<List<CountryResponse>?>

    private var instance: CountryApiClient? = null

    private var retrieveCountryRunnable: RetrieveCountryRunnable? = null

    constructor() {
        this.countryObject = countryObject
    }

    fun getInstance(): CountryApiClient? {
        if (instance == null) {
           instance = CountryApiClient()
        }
        return instance
    }

    fun getCountries(): MutableLiveData<List<CountryResponse>?> {
        return countryObject
    }


    fun searchCountryApi(query: String?) {
        if (retrieveCountryRunnable != null) {
            retrieveCountryRunnable = null
        }
        retrieveCountryRunnable = RetrieveCountryRunnable(query!!)
        val myHandler: Future<*> =
            AppExecutor.getInstance().networkIO()!!.submit(retrieveCountryRunnable)
        AppExecutor.getInstance().networkIO()!!.schedule(Runnable { //Canceling the retrofit call
            myHandler.cancel(true)
        }, 5000, TimeUnit.MILLISECONDS)
    }

    private inner class RetrieveCountryRunnable() : Runnable{

        lateinit var  query: String
         var cancelRequest: Boolean=false

        constructor(query: String) : this() {
            this.query = query
            cancelRequest = false;
        }


        override fun run() {
            try {
                val response: Response<*> = getCountries(query)!!.execute()
                if (cancelRequest) {
                    return
                }
        }catch (e : IOException){
            e.printStackTrace()
                countryObject.postValue(null)

        }

        }
        fun getCountries(query: String): Call<List<CountryResponse>>? {
            return Service.getCountryApi()?.searchCountries(query);
        }

        private fun setCancelRequest() {
            Log.d("CancelReq", "setCancelRequest: Cancelling Search Request")
            cancelRequest = true
        }


    }

}