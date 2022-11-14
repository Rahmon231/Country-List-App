package com.lemzeeyyy.countrylistapp.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.lemzeeyyy.countrylistapp.R
import com.lemzeeyyy.countrylistapp.activities.MainActivity
import com.lemzeeyyy.countrylistapp.adapter.CountryListAdapter
import com.lemzeeyyy.countrylistapp.adapter.FilterAdapter
import com.lemzeeyyy.countrylistapp.response.CountryResponse
import com.lemzeeyyy.countrylistapp.viewmodel.CountryViewModel


class FilterFragment : Fragment() {
     var countryViewModel : CountryViewModel = CountryViewModel()
    lateinit var recycler:RecyclerView
    lateinit var searchView:SearchView
    lateinit var dismissFilter: AppCompatImageButton
    lateinit var responses:List<CountryResponse>
    lateinit var adapter:FilterAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_filter, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeWidgets(view)
        getAllCountries()
        setupSearchView()
        observeChange()
        dismissFilter.setOnClickListener {
            var intent = Intent(view.context, MainActivity::class.java)
            startActivity(intent)
        }

    }

    private fun initializeWidgets(view: View) {
         searchView = view.findViewById(R.id.search_region)
         recycler = view.findViewById(R.id.filter_rv)
        dismissFilter = view.findViewById(R.id.dismiss_filter)
    }

    fun setupRecyclerView(it:List<CountryResponse>){
        adapter.setCountryList(it)
        recycler.layoutManager= LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL,false);
        recycler.adapter = adapter

    }
    fun observeChange(){
        // countryViewModel.setAllCountries()
        countryViewModel.getRegionalCountries().observe(viewLifecycleOwner, Observer {
            try {
                responses = it!!

                for (i in 0..it.size) {

                    setupRecyclerView(responses.sortedBy { it.name?.common })
                    Log.d("CheckRect", "getAllCountries: ${it[i].name?.common}")
                }
            }catch (e: java.lang.Exception){
                e.printStackTrace()
            }
        })

    }
    private fun setupSearchView(){

        searchView.setOnSearchClickListener {
            countryViewModel.getRegionalCountries()

        }
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(region: String): Boolean {
                countryViewModel.setRegionalCountries(region)
//                countryViewModel.getRegionalCountries().observe(viewLifecycleOwner, Observer {
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
//                })

                return false
            }


            override fun onQueryTextChange(newText: String): Boolean {
                countryViewModel.setRegionalCountries(newText)
//                countryViewModel.getRegionalCountries().observe(viewLifecycleOwner, Observer {
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
//                })

                return false
            }
        })
        searchView.setOnCloseListener {


            false
        }

    }

    private fun getAllCountries(){
        countryViewModel.setAllCountries()
        countryViewModel.getAllCountries().observe(viewLifecycleOwner, Observer {

            try {
                responses = it!!

                for (i in 0..it.size) {

                    setupRecyclerView(responses.sortedBy { it.name?.common })
                    Log.d("CheckRect", "getAllCountries: ${it[i].name?.common}")
                }
            }catch (e: java.lang.Exception){
                e.printStackTrace()
            }

        })

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
       responses = emptyList()
         adapter  = FilterAdapter(responses ,context)
    }

    override fun onDetach() {
        super.onDetach()
        responses = emptyList()
    }

}