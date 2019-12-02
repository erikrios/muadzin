package com.warokdroid.muadzin.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class City(

    @SerializedName("id")
    val id: String?,

    @SerializedName("nama")
    val name: String?
) : Parcelable