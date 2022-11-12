package com.lemzeeyyy.countrylistapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.lemzeeyyy.countrylistapp.API.CountryApi
import com.lemzeeyyy.countrylistapp.API.CountryApiClient
import com.lemzeeyyy.countrylistapp.model.CountryResponse
import com.lemzeeyyy.countrylistapp.utils.Credentials
import com.lemzeeyyy.countrylistapp.utils.Credentials.BASE_URL
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    lateinit var countryViewModel : CountryViewModel
    lateinit var repo: CountryRepository
    lateinit var apiClient: CountryApiClient
    lateinit var searchView : SearchView
    lateinit var recyclerA : RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        searchView = findViewById(R.id.search_view_country)
        recyclerA = findViewById(R.id.recycler_A)
        val retrofit = Retrofit.Builder()
            .baseUrl(Credentials.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        Log.d("baseCheck", "onCreate: $BASE_URL")


        val apiCall: CountryApi = retrofit.create(CountryApi::class.java)

//        countryViewModel = CountryViewModel()
//        countryViewModel.setAllCountries()
//        countryViewModel.getCountries().observe(this, Observer {
//            for (i in 0..5) {
//                Log.d("FinalCheck", "onCreate:  ${it?.get(i)?.name?.common}")
//            }
//        })
//        apiClient = CountryApiClient()
//        apiClient.getAllCountriesApi()
//        apiClient.getAllCountries().observe(this, Observer {
//            for (i in 0..5) {
//                Log.d("FinalCheck", "onCreate:  ${it?.get(i)?.name?.common}")
//            }
//        })
//        repo = CountryRepository()
//        repo.setAllCountriesApi()
//        repo.getAllCountries().observe(this, Observer {
//            for (i in 0..5) {
//                Log.d("RepoCheck", "onCreate:  ${it?.get(i)?.name?.common}")
//            }
//        })
        countryViewModel = CountryViewModel()
        countryViewModel.setAllCountries()
        countryViewModel.getAllCountries().observe(this, Observer {
            for (i in 0..5) {
                Log.d("ViewModelCheck", "onCreate:  ${it?.get(i)?.name?.common}")
            }
        })

    }

    fun searchForCountry(query:String){
     countryViewModel.searchCountryApi(query)
    }

    fun getAllCountries(){
        countryViewModel.setAllCountries()
        countryViewModel.getAllCountries().observe(this@MainActivity, Observer {
            for (i in 0..20) {
                Log.d("ViewModelCheck", "onCreate:  ${it?.get(i)?.name?.common}")
            }
        })
    }
    fun setupRecyclerView(){

    }
}