package com.lemzeeyyy.countrylistapp.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lemzeeyyy.countryapp.model.Bbd

class Currencies() : Parcelable {
    @SerializedName("BBD")
    @Expose
    var bbd: Bbd? = null

    constructor(parcel: Parcel) : this() {
        bbd = parcel.readParcelable(Bbd::class.java.classLoader)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(bbd, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Currencies> {
        override fun createFromParcel(parcel: Parcel): Currencies {
            return Currencies(parcel)
        }

        override fun newArray(size: Int): Array<Currencies?> {
            return arrayOfNulls(size)
        }
    }


}