package com.codewithmohsen.lastnews.presentation.uiModels

import android.os.Parcel
import android.os.Parcelable

data class UiArticle(
    var title: String,
    var author: String,
    var content: String,
    var description: String,
    var url: String,
    var urlToImage: String,
    var sourceName: String,
    var publishTime: String
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(author)
        parcel.writeString(content)
        parcel.writeString(description)
        parcel.writeString(url)
        parcel.writeString(urlToImage)
        parcel.writeString(sourceName)
        parcel.writeString(publishTime)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UiArticle> {
        override fun createFromParcel(parcel: Parcel): UiArticle {
            return UiArticle(parcel)
        }

        override fun newArray(size: Int): Array<UiArticle?> {
            return arrayOfNulls(size)
        }
    }

}