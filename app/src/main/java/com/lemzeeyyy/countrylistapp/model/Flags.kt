package com.lemzeeyyy.countrylistapp.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Flags() : Parcelable {
    @SerializedName("png")
    @Expose
    var png: String? = null

    @SerializedName("svg")
    @Expose
    var svg: String? = null

    constructor(parcel: Parcel) : this() {
        png = parcel.readString()
        svg = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(png)
        parcel.writeString(svg)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Flags> {
        override fun createFromParcel(parcel: Parcel): Flags {
            return Flags(parcel)
        }

        override fun newArray(size: Int): Array<Flags?> {
            return arrayOfNulls(size)
        }
    }
}