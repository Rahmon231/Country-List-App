package com.lemzeeyyy.countryapp.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose
import android.os.Parcel

class Bbd() : Parcelable {
    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("symbol")
    @Expose
    var symbol: String? = null

    constructor(parcel: Parcel) : this() {
        name = parcel.readString()
        symbol = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(symbol)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Bbd> {
        override fun createFromParcel(parcel: Parcel): Bbd {
            return Bbd(parcel)
        }

        override fun newArray(size: Int): Array<Bbd?> {
            return arrayOfNulls(size)
        }
    }


}