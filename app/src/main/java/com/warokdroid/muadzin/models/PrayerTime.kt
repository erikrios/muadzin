package com.warokdroid.muadzin.models

import com.google.gson.annotations.SerializedName

data class PrayerTime(

    @SerializedName("ashar")
    val ashar: String?,

    @SerializedName("dhuha")
    val dhuha: String?,

    @SerializedName("dzuhur")
    val dhuhur: String?,

    @SerializedName("imsak")
    val imsak: String?,

    @SerializedName("isya")
    val isya: String?,

    @SerializedName("maghrib")
    val maghrib: String?,

    @SerializedName("subuh")
    val subuh: String?,

    @SerializedName("tanggal")
    val date: String?,

    @SerializedName("terbit")
    val terbit: String?
)