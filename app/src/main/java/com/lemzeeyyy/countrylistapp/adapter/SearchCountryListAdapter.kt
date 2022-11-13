package com.lemzeeyyy.countrylistapp.adapter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lemzeeyyy.countrylistapp.activities.DetailsActivity
import com.lemzeeyyy.countrylistapp.R
import com.lemzeeyyy.countrylistapp.clickListener.CountryClickListener
import com.lemzeeyyy.countrylistapp.response.CountryResponse

class SearchCountryListAdapter(): RecyclerView.Adapter<SearchCountryListAdapter.CountryViewHolder>() {

    lateinit var data : List<CountryResponse>
    lateinit var context: Context
    var countryClickListener: CountryClickListener? = null

    constructor(data: List<CountryResponse>, context: Context) : this() {
        this.data = data
        this.context = context
    }


    class CountryViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView){

        var countryName : TextView = itemView.findViewById(R.id.name);
        var countryCapital : TextView = itemView.findViewById(R.id.capital);
        var countryFlag : ImageView = itemView.findViewById(R.id.flag_imageView)



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val view:View = LayoutInflater.from(context).inflate(R.layout.country_item,parent,false);
        return CountryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        try {
            holder.countryName.setText(data.get(position).name!!.common)
            holder.countryCapital.setText(data[position].capital!!.get(0))
            if(data[position].flags?.png !=null){
                Glide.with(context)
                    .load(data.get(position).flags?.png)
                    .into(holder.countryFlag);
            }else{
                holder.countryFlag.setImageResource(R.drawable.ic_launcher_background);
            }


        }catch (e : java.lang.Exception){
            e.printStackTrace()
        }

        holder.itemView.setOnClickListener {
            Toast.makeText(context, data.get(position).population.toString(), Toast.LENGTH_LONG)
                .show()
            countryClickListener?.onBookClickListener(position)
            val bundle:Bundle = Bundle()
            bundle.putParcelable("key", data[position])
            val intent:Intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra("bund",bundle)
            context.startActivity(intent)

        }
    }

    override fun getItemCount(): Int {
        if(data!=null){
            return data.size
        }
        return 0
    }

    fun setCountryList(data: List<CountryResponse> ){
        this.data = data
        notifyDataSetChanged()
    }

    fun getSelectedCountry(position: Int): CountryResponse? {
        if(data!=null){
            if (data.size>0){
                return data[position];
            }
        }
        return null
    }

}