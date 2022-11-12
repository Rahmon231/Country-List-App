package com.lemzeeyyy.countrylistapp.activities

import android.os.Bundle
import android.util.Log
import java.lang.Exception
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lemzeeyyy.countrylistapp.API.CountryApiClient
import com.lemzeeyyy.countrylistapp.R
import com.lemzeeyyy.countrylistapp.adapter.CountryListAdapter
import com.lemzeeyyy.countrylistapp.clickListener.CountryClickListener
import com.lemzeeyyy.countrylistapp.repository.CountryRepository
import com.lemzeeyyy.countrylistapp.response.CountryResponse
import com.lemzeeyyy.countrylistapp.viewmodel.CountryViewModel


class MainActivity : AppCompatActivity(), CountryClickListener {

    lateinit var countryViewModel : CountryViewModel
    lateinit var repo: CountryRepository
    lateinit var apiClient: CountryApiClient
    lateinit var searchView : SearchView
    lateinit var recyclerA : RecyclerView
     var responses: List<CountryResponse> = emptyList()
    var adapter: CountryListAdapter = CountryListAdapter(responses ,this@MainActivity)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeWidgets()
        initializeViewModel()
        getAllCountries()
        setupSearchView()

    }

    fun initializeWidgets(){
        searchView = findViewById(R.id.search_list)
        recyclerA = findViewById(R.id.countries_recyclerview)
    }

    fun initializeViewModel(){
        countryViewModel = CountryViewModel()
        countryViewModel.setAllCountries()
    }

    fun searchForCountry(query:String){
     countryViewModel.searchCountryApi(query)
    }

    fun getAllCountries(){
       initializeViewModel()
        countryViewModel.getAllCountries().observe(this, Observer {
            try {
                for (i in 0..it!!.size) {
                    if (it != null) {

                        setupRecyclerView(it)
                    }

                }
            }catch (e:Exception){
                e.printStackTrace()
            }

        })
    }


    fun setupRecyclerView(it:List<CountryResponse>){
        adapter.setCountryList(it)
        recyclerA.layoutManager=LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        recyclerA.adapter = adapter
    }

    fun setupSearchView(){
        initializeViewModel()
       // countryViewModel.searchCountryApi(query)
        searchView.setOnSearchClickListener {
            countryViewModel.getCountries()

        }
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                countryViewModel.searchCountryApi(query)
                countryViewModel.getCountries().observe(this@MainActivity, Observer {
                    for (i in 0..it!!.size) {
                        if (it != null) {
                            setupRecyclerView(it)
                        }
                    }
                  //  setupRecyclerView(it!!)
                    Log.d("CheckSearchedQUERY", "onQueryTextSubmit: ${it?.get(0)?.capital?.get(0)}")
                })

                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
        searchView.setOnCloseListener {
            countryViewModel.setAllCountries()
            countryViewModel.getAllCountries().observe(
                this, Observer {
                    try {
                        for (i in 0..it!!.size) {
                            if (it != null) {

                                setupRecyclerView(it)
                            }
                        }
                    }catch (e:Exception){
                        e.printStackTrace()
                    }
                }
            )
            false
        }

    }


    override fun onBookClickListener(position: Int) {
        val c: CountryResponse? = adapter.getSelectedCountry(position)
        Log.d("CLickedCountry", "onBookClickListener: ${c!!.name?.common}")
    }
}