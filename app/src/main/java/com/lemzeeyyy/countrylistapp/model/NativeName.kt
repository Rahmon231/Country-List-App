package com.lemzeeyyy.countrylistapp.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class NativeName():Parcelable {
    @SerializedName("eng")
    @Expose
    var eng: Eng? = null

    constructor(parcel: Parcel) : this() {
        eng = parcel.readParcelable(Eng::class.java.classLoader)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(eng, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<NativeName> {
        override fun createFromParcel(parcel: Parcel): NativeName {
            return NativeName(parcel)
        }

        override fun newArray(size: Int): Array<NativeName?> {
            return arrayOfNulls(size)
        }
    }
}