package com.lemzeeyyy.countrylistapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.lemzeeyyy.countrylistapp.model.CountryResponse
import java.util.Objects

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        val bundle:Bundle
        var intent: Intent = getIntent()


        var countryResponse:CountryResponse = intent.getBundleExtra("bund")?.getParcelable("key")!!;


        Log.d("Checknext", "onCreate: "+countryResponse.region)
    }
}