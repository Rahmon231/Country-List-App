package com.lemzeeyyy.countrylistapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.lang.Exception;
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.lemzeeyyy.countrylistapp.R
import com.lemzeeyyy.countrylistapp.response.CountryResponse

class DetailsActivity : AppCompatActivity() {
    lateinit var flag: ImageView
    lateinit var countryName: TextView
    lateinit var language: TextView
    lateinit var region: TextView
    lateinit var subregion: TextView
    lateinit var currency: TextView
    lateinit var pop: TextView
    lateinit var bundle: Bundle
    lateinit var countryResponse: CountryResponse

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
         bundle = Bundle()
        var intent: Intent = getIntent()
        countryResponse = intent.getBundleExtra("bund")?.getParcelable("key")!!;
        initializeWidgets();
        setWidgets()
        Log.d("lemzyyy", "onCreate: ${countryResponse.name?.common}")



    }

    private fun initializeWidgets() {
        flag = findViewById(R.id.country_flag)
        countryName = findViewById(R.id.name)
        language = findViewById(R.id.country_language)
        region = findViewById(R.id.country_region)
        subregion = findViewById(R.id.country_sub_region)
        currency = findViewById(R.id.country_currency)
        pop = findViewById(R.id.country_population)

    }
    private fun setWidgets(){
        try {
            Glide.with(this@DetailsActivity)
                .load(countryResponse.flags?.png)
                .into(flag);
            countryName.setText(countryResponse.name!!.common)
            language.setText(countryResponse.languages!!.eng)
            region.setText(countryResponse.region)
            subregion.setText(countryResponse.subregion)
            pop.setText(countryResponse.population.toString())
            currency.setText(countryResponse.currencies!!.bbd!!.name)



        }catch (e:Exception){
            e.printStackTrace()
        }

    }
}