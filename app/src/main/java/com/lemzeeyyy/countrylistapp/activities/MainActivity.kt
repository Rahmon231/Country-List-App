package com.lemzeeyyy.countrylistapp.activities

import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import android.view.View
import java.lang.Exception
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.SwitchCompat
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lemzeeyyy.countrylistapp.fragments.LanguageListFragment
import com.lemzeeyyy.countrylistapp.R
import com.lemzeeyyy.countrylistapp.adapter.CountryListAdapter
import com.lemzeeyyy.countrylistapp.adapter.SearchCountryListAdapter
import com.lemzeeyyy.countrylistapp.clickListener.CountryClickListener
import com.lemzeeyyy.countrylistapp.fragments.FilterFragment
import com.lemzeeyyy.countrylistapp.response.CountryResponse
import com.lemzeeyyy.countrylistapp.viewmodel.CountryViewModel


class MainActivity : AppCompatActivity(), CountryClickListener {

    lateinit var countryViewModel : CountryViewModel
    lateinit var searchView : SearchView
    lateinit var recyclerA : RecyclerView
    lateinit var search_rv:RecyclerView
    lateinit var btn: SwitchCompat
    lateinit var langBtn: AppCompatButton
    lateinit var filterBtn:AppCompatButton
    lateinit var layout: ConstraintLayout
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
        observeChange()

        langBtn.setOnClickListener {
            displayBottomSheetFragment()

        }
        filterBtn.setOnClickListener {
            displayFilterFragment()
        }


    }

    private fun displayBottomSheetFragment() {

        val dialog = LanguageListFragment()

        val view = layoutInflater.inflate(R.layout.fragment_language_list, null)

        dialog.setCancelable(true)
        dialog.show(supportFragmentManager,"LanguageFragment")
    }

    private fun displayFilterFragment() {
        layout.visibility = View.GONE
       supportFragmentManager.commit {
           setReorderingAllowed(true)
           add<FilterFragment>(R.id.fragment_container_view)
       }


    }

    private fun darkMode(){
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

    private fun initializeWidgets(){
        searchView = findViewById(R.id.search_list)
        recyclerA = findViewById(R.id.countries_recyclerview)
        btn = findViewById(R.id.day_button)
        langBtn = findViewById(R.id.language)
        filterBtn = findViewById(R.id.filter)
        layout = findViewById(R.id.constraint_layout)

    }

    private fun initializeViewModel(){
        countryViewModel = CountryViewModel()
        countryViewModel.setAllCountries()
    }

    fun searchForCountry(query:String){
     countryViewModel.searchCountryApi(query)
    }

    private fun getAllCountries(){
       initializeViewModel()
        countryViewModel.getAllCountries().observe(this, Observer {
            try {
                responses = it!!

                for (i in 0..it.size) {

                    setupRecyclerView(responses.sortedBy { it.name?.common })
                    Log.d("CheckRect", "getAllCountries: ${it[i].name?.common}")
                }
            }catch (e:Exception){
                e.printStackTrace()
            }

        })
        //observeChange()
    }


    fun setupRecyclerView(it:List<CountryResponse>){
        adapter.setCountryList(it)
        recyclerA.layoutManager=LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        recyclerA.adapter = adapter

    }

    fun observeChange(){
       // countryViewModel.setAllCountries()
        countryViewModel.getCountries().observe(this, Observer {
            try {
                responses = it!!

                for (i in 0..it.size) {

                    setupRecyclerView(responses.sortedBy { it.name?.common })
                    Log.d("CheckRect", "getAllCountries: ${it[i].name?.common}")
                }
            }catch (e:Exception){
                e.printStackTrace()
            }
        })

    }




    private fun setupSearchView(){
        initializeViewModel()
      //  countryViewModel.searchCountryApi(query)
        searchView.setOnSearchClickListener {
            //countryViewModel.getCountries()

        }
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                countryViewModel.searchCountryApi(query)
              //  countryViewModel.getCountries().observe(this@MainActivity, Observer {
//                    try {
//                        responses = it!!
//
//                        for (i in 0..it.size) {
//
//                            setupRecyclerView(responses.sortedBy { it.name?.common })
//                        }
//                    }catch (e:Exception){
//                        e.printStackTrace()
//                    }
//                    setupRecyclerView(it!!)
                    //observeChange()
                  //  Log.d("CheckSearchedQUERY", "onQueryTextSubmit: ${it?.get(0)?.capital?.get(0)}")
               // })

                return false
            }


            override fun onQueryTextChange(newText: String): Boolean {
                countryViewModel.searchCountryApi(newText)
//                countryViewModel.getCountries().observe(this@MainActivity, Observer {
//                    try {
//                        responses = it!!
//
//                        for (i in 0..it.size) {
//
//                            setupRecyclerView(responses.sortedBy { it.name?.common })
//                        }
//                    }catch (e:Exception){
//                        e.printStackTrace()
//                    }
//                    //  setupRecyclerView(it!!)
//                    Log.d("CheckSearchedQUERY", "onQueryTextSubmit: ${it?.get(0)?.capital?.get(0)}")
//                })
               // observeChange()
                return false
            }
        })
        searchView.setOnCloseListener {
//            countryViewModel.setAllCountries()
//            countryViewModel.getAllCountries().observe(
//                this, Observer {
//                    try {
//                        responses = it!!
//
//                        for (i in 0..it.size) {
//
//                            setupRecyclerView(responses.sortedBy { it.name?.common })
//                        }
//                    }catch (e:Exception){
//                        e.printStackTrace()
//                    }
//                }
//            )
            getAllCountries()
            false
        }

    }




    override fun onBookClickListener(position: Int) {
        val c: CountryResponse? = adapter.getSelectedCountry(position)
        Log.d("CLickedCountry", "onBookClickListener: ${c!!.name?.common}")
    }
}