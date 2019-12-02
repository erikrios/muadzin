package com.warokdroid.muadzin.apis

import android.net.Uri
import com.warokdroid.muadzin.BuildConfig

object BangHasanApi {

    fun getCity(): String {
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
            .appendPath("sholat")
            .appendPath("format")
            .appendPath("json")
            .appendPath("kota")
            .build()
            .toString()
    }

    fun getPrayerTime(cityId: String, date: String): String {
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
            .appendPath("sholat")
            .appendPath("format")
            .appendPath("json")
            .appendPath("jadwal")
            .appendPath("kota")
            .appendPath(cityId)
            .appendPath("tanggal")
            .appendPath(date)
            .build()
            .toString()

    }
}