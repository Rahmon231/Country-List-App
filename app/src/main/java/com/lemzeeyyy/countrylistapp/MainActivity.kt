package com.lemzeeyyy.countrylistapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.lemzeeyyy.countrylistapp.API.CountryApi
import com.lemzeeyyy.countrylistapp.model.CountryResponse
import com.lemzeeyyy.countrylistapp.utils.Credentials
import com.lemzeeyyy.countrylistapp.utils.Credentials.BASE_URL
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val retrofit = Retrofit.Builder()
            .baseUrl(Credentials.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        Log.d("baseCheck", "onCreate: $BASE_URL")

        val apiCall: CountryApi = retrofit.create(CountryApi::class.java)

        val call : Call<List<CountryResponse>> = apiCall.searchCountries("Nigeria")

        val call1 : Call<List<CountryResponse>> = apiCall.getAllCountries()

        call.enqueue(object  : Callback<List<CountryResponse>>{
            override fun onResponse(
                call: Call<List<CountryResponse>>,
                response: Response<List<CountryResponse>>
            ) {
                Log.d("CheckNigeria", "onResponse: ${response.body()?.get(0)?.flags?.png}")
            }

            override fun onFailure(call: Call<List<CountryResponse>>, t: Throwable) {
                Log.d("Response Failure", "onFailure: ${t.message
                }")
            }

        })


        call1.enqueue(object : Callback<List<CountryResponse>>{
            override fun onResponse(
                call: Call<List<CountryResponse>>,
                response: Response<List<CountryResponse>>
            ) {
                Log.d("AllResponse", "onResponse: ${response.body()?.get(0)?.subregion}")
            }

            override fun onFailure(call: Call<List<CountryResponse>>, t: Throwable) {
                Log.d("Response Failure", "onFailure: ${t.message
                }")
            }

        })



    }
}