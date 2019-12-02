package com.warokdroid.muadzin.models

import com.google.gson.annotations.SerializedName

data class DataPrayerTimeResponse(

    @SerializedName("jadwal")
    val jadwal: DataPrayerTime?
)