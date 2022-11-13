package com.lemzeeyyy.countrylistapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.lemzeeyyy.countrylistapp.R

class LanguageAdapter : RecyclerView.Adapter<LanguageAdapter.LanguageViewHolder> {
    class LanguageViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var language : AppCompatTextView = itemView.findViewById(R.id.language_name)
    }

    var languageList: List<String>
    var context:Context

    constructor(languageList: List<String>, context: Context) {
        this.languageList = languageList
        this.context = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LanguageViewHolder {
        val view:View = LayoutInflater.from(context).inflate(R.layout.language_list_item,parent,false)
        return LanguageViewHolder(view)
    }

    override fun onBindViewHolder(holder: LanguageViewHolder, position: Int) {
       holder.language.setText(languageList[position])
    }

    override fun getItemCount(): Int {
       return languageList.size
    }
    fun setCountryLanguageList(languageList: List<String> ){
        this.languageList = languageList
        notifyDataSetChanged()
    }
}