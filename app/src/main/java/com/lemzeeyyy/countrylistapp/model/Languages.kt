package com.lemzeeyyy.countrylistapp.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Languages() : Parcelable {

    @SerializedName("eng")
    @Expose
    var eng: String? = null

    constructor(parcel: Parcel) : this() {
        eng = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(eng)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Languages> {
        override fun createFromParcel(parcel: Parcel): Languages {
            return Languages(parcel)
        }

        override fun newArray(size: Int): Array<Languages?> {
            return arrayOfNulls(size)
        }
    }
}