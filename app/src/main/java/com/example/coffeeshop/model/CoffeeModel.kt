package com.example.coffeeshop.model

import android.os.Parcel
import android.os.Parcelable

data class CoffeeModel(
    val categoryId: String = "",
    val description: String = "",
    val extra: String = "",
    val picUrl: ArrayList<String> = ArrayList(),
    val price: Double = 0.0,
    val title: String = ""
) : Parcelable {
    constructor(parcel: Parcel) : this (
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.createStringArrayList() as ArrayList<String>,
        parcel.readDouble(),
        parcel.readString().toString()

    )

    override fun describeContents() = 0

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(categoryId)
        parcel.writeString(description)
        parcel.writeString(extra)
        parcel.writeStringList(picUrl)
        parcel.writeDouble(price)
        parcel.writeString(title)
    }

    companion object CREATOR : Parcelable.Creator<CoffeeModel> {
        override fun createFromParcel(parcel: Parcel): CoffeeModel {
            return CoffeeModel(parcel)
        }

        override fun newArray(p0: Int): Array<out CoffeeModel?>? {
            return arrayOfNulls(p0)
        }
    }
}