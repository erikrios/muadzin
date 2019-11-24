package com.warokdroid.muadzin.models

import com.google.gson.annotations.SerializedName

data class CityResponse(

    @SerializedName("kota")
    val cities: List<City>?
)