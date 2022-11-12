package com.lemzeeyyy.countrylistapp.response

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lemzeeyyy.countrylistapp.model.Currencies
import com.lemzeeyyy.countrylistapp.model.Flags
import com.lemzeeyyy.countrylistapp.model.Languages
import com.lemzeeyyy.countrylistapp.model.Name

class CountryResponse() : Parcelable {
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
    var languages: Languages? = null

    @SerializedName("flags")
    @Expose
    var flags: Flags? = null

    constructor(parcel: Parcel) : this() {
        name = parcel.readParcelable(Name::class.java.classLoader)
        currencies = parcel.readParcelable(Currencies::class.java.classLoader)
        capital = parcel.createStringArrayList()
        region = parcel.readString()
        subregion = parcel.readString()
        languages = parcel.readParcelable(Languages::class.java.classLoader)
        flags = parcel.readParcelable(Flags::class.java.classLoader)
    }

    override fun writeToParcel(parcel: Parcel, flag: Int) {
        parcel.writeParcelable(name, flag)
        parcel.writeParcelable(currencies, flag)
        parcel.writeStringList(capital)
        parcel.writeString(region)
        parcel.writeString(subregion)
        parcel.writeParcelable(languages, flag)
        parcel.writeParcelable(flags, flag)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CountryResponse> {
        override fun createFromParcel(parcel: Parcel): CountryResponse {
            return CountryResponse(parcel)
        }

        override fun newArray(size: Int): Array<CountryResponse?> {
            return arrayOfNulls(size)
        }
    }


}