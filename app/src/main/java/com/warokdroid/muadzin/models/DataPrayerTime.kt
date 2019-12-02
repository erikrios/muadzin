package com.warokdroid.muadzin.models

import com.google.gson.annotations.SerializedName

data class DataPrayerTime(

    @SerializedName("status")
    val status: String?,

    @SerializedName("data")
    val data: PrayerTime?
)