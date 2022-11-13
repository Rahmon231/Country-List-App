package com.lemzeeyyy.countrylistapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.lemzeeyyy.countrylistapp.R
import com.lemzeeyyy.countrylistapp.adapter.LanguageAdapter


class LanguageListFragment : BottomSheetDialogFragment() {
    private lateinit var langRecyclerView: RecyclerView
    lateinit var adapter:LanguageAdapter
    private lateinit var btn:AppCompatImageButton
    var languageList: List<String> = listOf(
        "English", "Spanish", "Bahasa", "Japanese","Deutsch," +
            "Espanol","French","Italian","Portuguese","Bengali","Turkish","Ukrainian","Czech"
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_language_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        langRecyclerView = view.findViewById(R.id.language_recyclerview)
        btn = view.findViewById(R.id.dismiss)
        loadLanguages()
        btn.setOnClickListener {
            dismiss()
        }

    }
    private fun loadLanguages() {
        langRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false);

        adapter = LanguageAdapter(languageList, requireContext())
        adapter.setCountryLanguageList(languageList)
        langRecyclerView.adapter = adapter
    }
}