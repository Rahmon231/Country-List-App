package com.lemzeeyyy.countrylistapp.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Eng() : Parcelable {
    @SerializedName("official")
    @Expose
    var official: String? = null

    @SerializedName("common")
    @Expose
    var common: String? = null

    constructor(parcel: Parcel) : this() {
        official = parcel.readString()
        common = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(official)
        parcel.writeString(common)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Eng> {
        override fun createFromParcel(parcel: Parcel): Eng {
            return Eng(parcel)
        }

        override fun newArray(size: Int): Array<Eng?> {
            return arrayOfNulls(size)
        }
    }
}