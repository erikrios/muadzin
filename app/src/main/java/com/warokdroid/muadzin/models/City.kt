package com.warokdroid.muadzin.models

import com.google.gson.annotations.SerializedName

data class City(

    @SerializedName("id")
    val id: String?,

    @SerializedName("nama")
    val name: String?
)