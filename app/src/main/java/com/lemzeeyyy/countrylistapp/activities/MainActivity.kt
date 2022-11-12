package com.lemzeeyyy.countrylistapp.activities

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import java.lang.Exception
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.SwitchCompat
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
    lateinit var btn: SwitchCompat
    var darkMode: String? = null
     var responses: List<CountryResponse> = emptyList()
    var adapter: CountryListAdapter = CountryListAdapter(responses ,this@MainActivity)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        initializeWidgets()
        darkMode()
        initializeViewModel()
        getAllCountries()
        setupSearchView()

    }

    fun darkMode(){
        btn.setOnCheckedChangeListener { _, isChecked ->
            // if the button is checked, i.e., towards the right or enabled
            // enable dark mode, change the text to disable dark mode
            // else keep the switch text to enable dark mode
            if (btn.isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                btn.text = "Disable dark mode"
                darkMode = "Disable dark Mode"
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                btn.text = "Enable dark mode"
                darkMode = "Enable dark Mode"
            }
        }

    }

    fun initializeWidgets(){
        searchView = findViewById(R.id.search_list)
        recyclerA = findViewById(R.id.countries_recyclerview)
        btn = findViewById(R.id.day_button)
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