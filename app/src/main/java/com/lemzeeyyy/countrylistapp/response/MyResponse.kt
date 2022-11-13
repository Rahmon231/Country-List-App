package com.lemzeeyyy.countrylistapp.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lemzeeyyy.countrylistapp.model.Currencies
import com.lemzeeyyy.countrylistapp.model.Flags
import com.lemzeeyyy.countrylistapp.model.Languages
import com.lemzeeyyy.countrylistapp.model.Name

class MyResponse {
    @SerializedName("name")
    @Expose
    var name: Name? = null

    @SerializedName("currencies")
    @Expose
    var currencies: Currencies? = null

    @SerializedName("capital")
    @Expose
    var capital: List<String?>? = null

    @SerializedName("region")
    @Expose
    var region: String? = null

    @SerializedName("subregion")
    @Expose
    var subregion: String? = null

    @SerializedName("languages")
    @Expose
    var languages: List<Languages?>? = null

    @SerializedName("population")
    @Expose
    var population: Int = 0

    @SerializedName("flags")
    @Expose
    var flags: Flags? = null

    constructor(
        name: Name?,
        currencies: Currencies?,
        capital: List<String?>?,
        region: String?,
        subregion: String?,
        languages: List<Languages?>,
        population: Int,
        flags: Flags?
    ) {
        this.name = name
        this.currencies = currencies
        this.capital = capital
        this.region = region
        this.subregion = subregion
        this.languages = languages
        this.population = population
        this.flags = flags
    }

    fun getResponseName() : Name? {
        return name
    }
    fun getResponseCurrency() : Currencies? {
        return currencies
    }
    fun getResponseCapital() : List<String?>? {
        return capital
    }
    fun getResponseRegion() : String? {
        return region
    }
    fun getResponseSubRegion() : String? {
        return subregion
    }
    fun getResponseLanguages() : List<Languages?>? {
        return languages
    }

    fun getResponsePopulation() : Int? {
        return population
    }

    fun getResponseFlag() : Flags? {
        return flags
    }


}