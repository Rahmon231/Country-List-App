package com.lemzeeyyy.countrylistapp.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lemzeeyyy.countrylistapp.response.LanguageResponse

class Languages() : Parcelable {

    @SerializedName("eng")
    @Expose
    var eng: String? = null

    @SerializedName("spa")
    @Expose
    var spa:String? = null

    @SerializedName("fra")
    @Expose
    var fra:String? = null

    @SerializedName("deu")
    @Expose
    var deu:String? = null

    @SerializedName("ita")
    @Expose
    var ita:String? = null

    @SerializedName("ces")
    @Expose
    var ces:String? = null

    @SerializedName("por")
    @Expose
    var por:String? = null

    @SerializedName("nld")
    @Expose
    var nld:String? = null

    @SerializedName("ara")
    @Expose
    var ara:String? = null

    @SerializedName("jpn")
    @Expose
    var jpn:String? = null

    @SerializedName("prs")
    @Expose
    var prs:String? = null

    @SerializedName("pus")
    @Expose
    var pus:String? = null






    constructor(parcel: Parcel) : this() {
        eng = parcel.readString()
        spa = parcel.readString()
        fra = parcel.readString()
        deu = parcel.readString()
        ita = parcel.readString()
        ces = parcel.readString()
        por = parcel.readString()
        nld = parcel.readString()
        ara = parcel.readString()
        jpn = parcel.readString()
        prs = parcel.readString()
        pus = parcel.readString()

    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(eng)
        parcel.writeString(spa)
        parcel.writeString(fra)
        parcel.writeString(deu)
        parcel.writeString(ita)
        parcel.writeString(ces)
        parcel.writeString(por)
        parcel.writeString(nld)
        parcel.writeString(ara)
        parcel.writeString(jpn)
        parcel.writeString(prs)
        parcel.writeString(pus)

    }

    override fun describeContents(): Int {
        return 0
    }

    override fun toString(): String {
        return "Languages(eng=$eng, spa=$spa, fra=$fra, deu=$deu, ita=$ita, ces=$ces, por=$por, nld=$nld, ara=$ara, jpn=$jpn, prs=$prs, pus=$pus)"
    }




    companion object CREATOR : Parcelable.Creator<Languages> {
        override fun createFromParcel(parcel: Parcel): Languages {
            return Languages(parcel)
        }

        override fun newArray(size: Int): Array<Languages?> {
            return arrayOfNulls(size)
        }
//        public fun getAllLangs() : List<String>? {
//             var allLang: List<String>? = emptyList()
//            allLang = listOf("eng","spa","fra","deu","ita","ces","por","nld","ara","jpn","prs","pus")
//            return allLang
//        }
    }


}