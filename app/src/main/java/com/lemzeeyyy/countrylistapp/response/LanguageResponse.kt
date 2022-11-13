package com.lemzeeyyy.countrylistapp.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lemzeeyyy.countrylistapp.model.Languages

class LanguageResponse {
    @SerializedName("languages")
    @Expose
    var languages: List<Languages>? = null

    constructor(languages: List<Languages>?) {
        this.languages = languages
    }

 fun getAllLanguages() : List<Languages>? {
    return languages;
}
}