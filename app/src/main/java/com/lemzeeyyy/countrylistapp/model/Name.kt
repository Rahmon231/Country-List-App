package com.lemzeeyyy.countrylistapp.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Name() : Parcelable {
    @SerializedName("common")
    @Expose
    var common: String? = null

    @SerializedName("official")
    @Expose
    var official: String? = null

    @SerializedName("nativeName")
    @Expose
    var nativeName: NativeName? = null

    constructor(parcel: Parcel) : this() {
        common = parcel.readString()
        official = parcel.readString()
        nativeName = parcel.readParcelable(NativeName::class.java.classLoader)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(common)
        parcel.writeString(official)
        parcel.writeParcelable(nativeName, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Name> {
        override fun createFromParcel(parcel: Parcel): Name {
            return Name(parcel)
        }

        override fun newArray(size: Int): Array<Name?> {
            return arrayOfNulls(size)
        }
    }
}